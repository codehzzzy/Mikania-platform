package com.zjxz.mikaniaplatform.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子状态更新类
 */
@ApiModel("帖子状态更新类")
@Data
@NoArgsConstructor
public class PostInfoUploadStatusRequest{

    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("相似程度")
    private String similarity;

    @ApiModelProperty("状态(0:待检测;1:是;2:否)")
    private Integer status;
}
