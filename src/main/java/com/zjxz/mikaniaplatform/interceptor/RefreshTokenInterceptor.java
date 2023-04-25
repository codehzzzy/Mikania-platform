package com.zjxz.mikaniaplatform.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.zjxz.mikaniaplatform.constants.RedisConstant;
import com.zjxz.mikaniaplatform.model.dto.UserDTO;
import com.zjxz.mikaniaplatform.model.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.zjxz.mikaniaplatform.constants.Common.HEADER_TOKEN;
import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.removeUser;
import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.saveUser;

/**
 * @author zh
 * @Date 2023/4/22
 * @description 第一层拦截
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private static final int LOGIN_USER_TTL=30;

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取请求头中的token
        String token = request.getHeader(HEADER_TOKEN);
        if (StrUtil.isBlank(token)) {
            return true;
        }
        String key  = RedisConstant.USER_LOGIN_TOKEN + token;
        //Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        String jsonUser = stringRedisTemplate.opsForValue().get(key);
        User user = JSONUtil.toBean(jsonUser, User.class);
        if (BeanUtil.isEmpty(user)) {
            return true;
        }
        UserDTO UserDTO = BeanUtil.toBean(user,UserDTO.class);
        // 存在，保存用户信息到 ThreadLocal
        saveUser(UserDTO);
        // 刷新有效期
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 放行
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        removeUser();
    }
}
