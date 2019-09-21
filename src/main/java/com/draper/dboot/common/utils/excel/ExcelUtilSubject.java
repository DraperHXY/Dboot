package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 某种层面来说，这个框架很活
 * 即使对 Excel 的输出不同的报错信息
 * 也可以通过覆写自定义做到
 *
 * @author draper_hxy
 */
@Slf4j
public class
ExcelUtilSubject<T> {

    public void read(InputStream inputStream, Class<T> clazz, AnalysisEventListener<T> listener) throws Exception {
        try {
            EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
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

    /**
     * 本地写
     */
    public OutputStream localWrite(List<T> dataList, Class<T> clazz, String fileName) {
        ExcelWriter excelWriter = EasyExcel.write(fileName, clazz).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.write(dataList, writeSheet);
        excelWriter.finish();
        return null;
    }

    /**
     * Web写
     */
    public void webWrite(HttpServletResponse response, Class<T> clazz, List<T> dataList, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        EasyExcel.write(response.getOutputStream(), clazz).sheet().doWrite(dataList);

    }

}
