package com.zjxz.mikaniaplatform.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * 科普信息表
 * @author hzzzzzy
 * @TableName popularization_science
 */
@TableName(value ="popularization_science")
@Data
@Builder
public class PopularizationScience implements Serializable {
    /**
     * 科普信息编号
     */
    @TableId
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章路径
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}