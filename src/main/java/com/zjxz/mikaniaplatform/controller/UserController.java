package com.zjxz.mikaniaplatform.controller;


import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.dto.UserAddRequest;
import com.zjxz.mikaniaplatform.model.dto.UserAltPassAddRequest;
import com.zjxz.mikaniaplatform.model.dto.UserRegAddRequest;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;


/**
 * @author zh
 * @date 2023/4/22
 * @description 用户校验控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(
            @RequestBody
            @Parameter(description = "登录请求", required = true)
            @NotEmpty
            UserAddRequest userAddRequest
    ){
        String token = userService.doLogin(userAddRequest.getUsername(), userAddRequest.getPassword());
        if ("".equals(token)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("token为空"));
        }
        return new Result<String>().success().message("登录成功").data(token);
    }


    @ApiOperation("登出")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        userService.doLogout(request);
        return new Result<>().success().message("登出成功");
    }


    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(
            @RequestBody
            @Parameter(description = "注册的用户名和密码", required = true)
            @NotEmpty
            UserRegAddRequest userRegAddRequest
    ){
        userService.doRegister(userRegAddRequest.getUsername(), userRegAddRequest.getPassword(), userRegAddRequest.getVerifyPassword());
        return new Result<>().success().message("注册成功");
    }

    @ApiOperation("修改密码")
    @PostMapping("/alterPassword")
    public Result alterPassword(
            @RequestBody
            @Parameter(description = "原密码和修改的密码", required = true)
            @NotEmpty
            UserAltPassAddRequest userAltPassAddRequest,
            HttpServletRequest request
    ){
        userService.doAlter(request,userAltPassAddRequest.getOldPassword(),userAltPassAddRequest.getNewPassword(),userAltPassAddRequest.getNewVerifyPassword());
        return new Result<>().success().message("修改成功");
    }

}
