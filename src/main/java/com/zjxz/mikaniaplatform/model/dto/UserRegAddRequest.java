package com.zjxz.mikaniaplatform.model.dto;


/**
 * 注册请求信息
 * @author zh
 * @param username
 * @param password
 * @param verifyPassword
 */
public record UserRegAddRequest(
        /**
         * 注册用户名
         */
        String username,
        /**
         * 注册密码
         */
        String password,
        /**
         * 校验密码
         */
        String verifyPassword
) {
}
