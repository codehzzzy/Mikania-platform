package com.zjxz.mikaniaplatform.model.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子上传请求
 */
@ApiModel("帖子上传请求类")
@Data
@NoArgsConstructor
public class PostInfoAddRequest {
    @ApiModelProperty("省编号")
    private Integer provinceId;

    @ApiModelProperty("城市编号")
    private Integer cityId;

    @ApiModelProperty("详细地址")
    private String detail;

    @ApiModelProperty("文件url")
    private String url;
}