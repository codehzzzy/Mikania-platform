package com.zjxz.mikaniaplatform.model.dto;


/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子上传请求
 */
public record PostInfoAddRequest(
        /**
         * 省名称
         */
        String province,

        /**
         * 城市名称
         */
        String city,

        /**
         * 详细地址
         */
        String detail
) {}
