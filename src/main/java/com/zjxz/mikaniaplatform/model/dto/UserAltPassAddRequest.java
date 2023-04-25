package com.zjxz.mikaniaplatform.model.dto;


public record UserAltPassAddRequest(
        /**
         * 原密码
         */
        String oldPassword,
        /**
         * 新密码
         */
        String newPassword,
        /**
         * 新密码确认密码
         */
        String newVerifyPassword
) {
}
