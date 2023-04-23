package com.zjxz.mikaniaplatform.model.dto;


/**
 * @author hzzzzzy
 * @date 2023/4/23
 * @description 帖子更新请求
 */
public record PostInfoUpdateRequest (
        /**
         * 发布信息编号
         */
        Integer id,

        /**
         * 图片路径
         */
        String url
)
{}
