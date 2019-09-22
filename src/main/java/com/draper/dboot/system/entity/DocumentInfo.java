package com.draper.dboot.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.draper.dboot.system.entity.beans.Document;
import lombok.Data;

/**
 * @author draper_hxy
 */
@Data
public class DocumentInfo extends Document {

    @ExcelProperty(index = 3)
    private String message;

}
