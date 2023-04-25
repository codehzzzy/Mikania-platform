package com.zjxz.mikaniaplatform.constants;

/**
 * @author zh
 * @Date 2023/4/22
 * @description Redis的key前缀
 */
public interface RedisConstant {

    /**
     * 用户登录token
     */
    String USER_LOGIN_TOKEN="login:token:";

    /**
     * 用户登录token过期时间
     */
    Integer USER_LOGIN_TOKEN_EXPIRE = 60 * 60 * 24 * 7;
}
