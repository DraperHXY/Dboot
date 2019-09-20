package com.draper.dboot.common.util.excel;

import com.alibaba.excel.event.AnalysisEventListener;
import com.draper.dboot.common.utils.excel.ExcelExecuteStrategy;
import com.draper.dboot.common.utils.excel.ExcelUtilSubject;
import com.draper.dboot.common.utils.excel.MyAbstractExcelDetailEventExecutor;
import com.draper.dboot.common.utils.excel.MyAbstractExcelSimpleEventExecutor;
import com.draper.dboot.system.entity.beans.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author draper_hxy
 */
public class ExcelTest {

    @Test
    public void testAnalysis() throws Exception {
        File file = new File("Excel测试数据.xlsx");
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        AnalysisEventListener<Document> listener = new MyAbstractExcelSimpleEventExecutor(ExcelExecuteStrategy.CONTINUE);

        new ExcelUtilSubject<Document>().upload(fileInputStream, Document.class, listener);
    }


}
