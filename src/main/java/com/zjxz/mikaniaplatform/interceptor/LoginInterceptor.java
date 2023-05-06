package com.zjxz.mikaniaplatform.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.zjxz.mikaniaplatform.model.dto.UserDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.getUser;

/**
 * @author zh
 * @Date 2023/4/22
 * @description 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO userDTO = getUser();
        if(BeanUtil.isEmpty(userDTO)){
            response.setStatus(401);
            return false;
        }else {
            return true;
        }
    }
}
