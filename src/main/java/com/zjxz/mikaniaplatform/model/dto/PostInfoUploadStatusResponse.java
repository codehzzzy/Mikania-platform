package com.zjxz.mikaniaplatform.model.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子状态更新 response
 */
@Data
@NoArgsConstructor
public class PostInfoUploadStatusResponse {
    /**
     * 发布信息编号
     */
    private Integer id;

    /**
     * 图片路径
     */
    private String url;
}
