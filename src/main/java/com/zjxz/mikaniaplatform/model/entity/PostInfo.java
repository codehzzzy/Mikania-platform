package com.zjxz.mikaniaplatform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 发布信息表
 * @TableName post_info
 */
@TableName(value ="post_info")
@Data
@Builder
public class PostInfo implements Serializable {
    /**
     * 主键(发布信息编号)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 省编号
     */
    @TableField(value = "pro_id")
    private Integer proId;

    /**
     * 市编号
     */
    @TableField(value = "city_id")
    private Integer cityId;

    /**
     * 详细地址
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 0:待检测;1:是;2:否
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 相似程度
     */
    @TableField(value = "similarity")
    private String similarity;

    /**
     * 图片路径
     */
    @TableField(value = "url")
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}