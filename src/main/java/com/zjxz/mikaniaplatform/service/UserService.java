package com.zjxz.mikaniaplatform.service;

import com.zjxz.mikaniaplatform.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

/**
* @author hzzzzzy
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-04-21 16:54:07
*/
public interface UserService extends IService<User> {

    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean doLogin(String username,String password);

    /**
     * 登出
     * @return
     */
    void doLogout(HttpServletRequest request);

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param verifyPassword 确认密码
     */
    void doRegister(String username,String password,String verifyPassword);

    /**
     * 修改密码
     *
     * @param request request
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param verifyPassword 确认密码
     */
    void doAlter(HttpServletRequest request,String oldPassword,String newPassword,String verifyPassword);
}
