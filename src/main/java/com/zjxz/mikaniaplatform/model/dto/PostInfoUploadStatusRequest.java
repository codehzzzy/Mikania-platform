package com.zjxz.mikaniaplatform.model.dto;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子状态更新 request
 */
public record PostInfoUploadStatusRequest(
        /**
         * 编号
         */
        Integer id,

        /**
         * 相似程度
         */
        String similarity,

        /**
         * 0:待检测;1:是;2:否
         */
        Integer status
){}
