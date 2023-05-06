package com.zjxz.mikaniaplatform.model.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("注册请求信息")
@Builder
@Data
public class UserRegAddRequest{
    @ApiModelProperty("")
    String username;

    @ApiModelProperty("注册密码")
    String password;

    @ApiModelProperty("校验密码")
    String verifyPassword;
}
