package com.zjxz.mikaniaplatform.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 帖子返回vo
 */
@Data
public class UserPostInfoVO {
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

    private static final long serialVersionUID = 1L;
}