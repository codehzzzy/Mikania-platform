package com.zjxz.mikaniaplatform.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hzzzzzy
 * @date 2023/4/23
 * @description 帖子更新请求
 */
@ApiModel("帖子更新请求")
@Data
@NoArgsConstructor
public class PostInfoUpdateRequest {

    @ApiModelProperty("发布信息编号")
    Integer id;

    @ApiModelProperty("图片路径")
    String url;
}
