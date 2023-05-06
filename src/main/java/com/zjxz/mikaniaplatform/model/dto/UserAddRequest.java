package com.zjxz.mikaniaplatform.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录请求信息
 * @author zh
 */
@Data
@NoArgsConstructor
@ApiModel("登录请求信息")
public class UserAddRequest{

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("密码")
    String password;
}
