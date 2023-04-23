package com.zjxz.mikaniaplatform.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 发布信息表
 * @TableName post_info
 */
@TableName(value ="post_info")
@Data
public class PostInfo implements Serializable {
    /**
     * 发布信息编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 省编号
     */
    private Integer proId;

    /**
     * 市编号
     */
    private Integer cityId;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 0:待检测;1:是;2:否
     */
    private Integer status;

    /**
     * 相似程度
     */
    private String similarity;

    /**
     * 图片路径
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}