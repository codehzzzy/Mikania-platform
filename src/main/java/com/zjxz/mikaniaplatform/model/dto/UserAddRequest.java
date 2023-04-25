package com.zjxz.mikaniaplatform.model.dto;


/**
 * 登录请求信息
 * @author zh
 * @param username
 * @param password
 */
public record UserAddRequest(
        /**
         * 用户名
         */
        String username,

        /**
         * 密码
         */
        String password
) {
}
