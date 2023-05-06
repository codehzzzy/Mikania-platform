package com.zjxz.mikaniaplatform.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子上传请求
 */
@ApiModel("帖子上传请求类")
@Data
public class PostInfoAddRequest {
    @ApiModelProperty("省名称")
    private String province;

    @ApiModelProperty("城市名称")
    private String city;

    @ApiModelProperty("详细地址")
    private String detail;

    @ApiModelProperty("文件url")
    private String url;
}