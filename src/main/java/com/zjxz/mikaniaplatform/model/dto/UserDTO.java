package com.zjxz.mikaniaplatform.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
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