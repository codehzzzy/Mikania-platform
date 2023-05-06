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
 * 科普信息表
 * @TableName popularization_science
 */
@TableName(value ="popularization_science")
@Data
@Builder
public class PopularizationScience implements Serializable {
    /**
     * 主键(科普信息编号)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章路径
     */
    @TableField(value = "url")
    private String url;

    /**
     * 文章发表时间
     */
    @TableField(value = "time")
    private Date time;

    /**
     * 帖子类型(1:危害;2:政策;3:防护手段;4:植株形态)
     */
    @TableField(value = "type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}