package com.zjxz.mikaniaplatform.enums;


import com.zjxz.mikaniaplatform.util.ResultCode;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 业务枚举类
 */
public enum BusinessFailCode implements ResultCode {

    /**
     * 权限不足，无法访问
     */
    PERMISSION_DENIED(3001, "权限不足，无法访问"),

    /**
     * 参数为空
     */
    PARAMETER_EMPTY(3002, "参数为空"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(3003, "参数错误"),

    /**
     * 请求方式错误
     */
    REQUEST_METHOD_NOT_SUPPORTED(3004, "请求方式错误"),

    /**
     * 数据获取失败
     */
    DATA_FETCH_ERROR(3005, "数据获取失败"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR(3005, "文件上传失败"),

    /**
     * 文件下载失败
     */
    FILE_DOWNLOAD_ERROR(3005, "文件下载失败"),

    /**
     * 对象类型错误
     */
    OBJECT_TYPE_ERROR(3006, "对象类型错误"),


    /**
     * 用户名或密码错误
     */
    LOGIN_DATA_ERROR(4000,"用户名或密码错误"),


    /**
     * 密码与确认密码不匹配
     */
    REGISTER_PASSWORD_ERROR(4001,"密码与确认密码不匹配"),
    /**
     * 注册的用户名已存在
     */
    REGISTER_USERNAME_EXIST(4002,"注册的用户名已存在"),

    OLD_PASSWORD_ERROR(4003,"旧密码错误"),



    ;



    /**
     * 编号
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    BusinessFailCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
