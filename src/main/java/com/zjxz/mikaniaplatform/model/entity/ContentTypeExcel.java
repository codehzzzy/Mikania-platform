package com.zjxz.mikaniaplatform.model.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author hzzzzzy
 * @date 2023/4/11
 * @description ContentType对应的 excel 实体类
 */
@Data
public class ContentTypeExcel {
    /**
     * 文件扩展名
     */
    @ExcelProperty("FileExtension")
    private String fileExtension;

    /**
     * 类型
     */
    @ExcelProperty("Content-Type")
    private String type;
}
