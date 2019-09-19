package com.draper.dboot.system.entity.beans;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author draper_hxy
 */
@Data
public class Document extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String remark;
}
