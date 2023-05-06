package com.zjxz.mikaniaplatform.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.zjxz.mikaniaplatform.constants.Common;
import com.zjxz.mikaniaplatform.constants.RedisConstant;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.model.entity.User;
import org.apache.commons.collections4.BagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hzzzzzy
 * @date 2023/5/5
 * @description getCurrentUserUtil
 */
@Component
@SuppressWarnings("all")
public class UserUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    public User getCurrentUser(HttpServletRequest request){
        String token = request.getHeader(Common.HEADER_TOKEN);
        String key  = RedisConstant.USER_LOGIN_TOKEN + token;
        String jsonUser = stringRedisTemplate.opsForValue().get(key);
        User user = JSONUtil.toBean(jsonUser, User.class);
        if (BeanUtil.isEmpty(user)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("获取用户数据失败"));
        }
        return user;
    }
}
