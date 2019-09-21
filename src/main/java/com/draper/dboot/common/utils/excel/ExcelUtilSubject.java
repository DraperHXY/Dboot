package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author draper_hxy
 */
@Slf4j
public class ExcelUtilSubject<T extends BaseRowModel> {

    public void parse(InputStream inputStream, Class<T> clazz, AnalysisEventListener<T> listener) throws Exception {
        try {
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 0, clazz), listener);
        } catch (Exception e) {
            log.error("Excel 上传失败，{}", e);
            throw new Exception(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("Excel InputStream 关闭失败,{}", e);
            }
        }
    }

}
