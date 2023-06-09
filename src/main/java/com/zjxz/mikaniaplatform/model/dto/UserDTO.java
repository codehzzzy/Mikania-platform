package com.zjxz.mikaniaplatform.model.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @author hzzzzzy
 * @description 存储在ThreadLocal
 */
@Data
public class UserDTO implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}