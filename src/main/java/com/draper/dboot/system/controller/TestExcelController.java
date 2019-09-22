package com.draper.dboot.system.controller;

import com.alibaba.excel.event.AnalysisEventListener;
import com.draper.dboot.common.utils.GenericListener;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.common.utils.excel.AbstractExcelSimpleEventExecutor;
import com.draper.dboot.common.utils.excel.ExcelExecuteStrategy;
import com.draper.dboot.common.utils.excel.ExcelUtilSubject;
import com.draper.dboot.common.utils.excel.MyAbstractExcelDetailEventExecutor;
import com.draper.dboot.system.entity.DocumentInfo;
import com.draper.dboot.system.entity.beans.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author draper_hxy
 */
@RestController
public class TestExcelController {

    @PostMapping("/test/uploadExcel")
    public void testUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {

        AnalysisEventListener<Document> listener = new MyAbstractExcelDetailEventExecutor(new GenericListener<List<DocumentInfo>, HttpServletResponse>() {
            @Override
            public HttpServletResponse callback(List<DocumentInfo> documentInfos) {
                try {
                    new ExcelUtilSubject<DocumentInfo>().webWrite(response, DocumentInfo.class, documentInfos, "失败数据");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        try {
            new ExcelUtilSubject<Document>().read(file.getInputStream(), Document.class, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
