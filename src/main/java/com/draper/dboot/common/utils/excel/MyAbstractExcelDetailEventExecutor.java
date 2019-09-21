package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSONObject;
import com.draper.dboot.common.utils.GenericListener;
import com.draper.dboot.system.entity.beans.Document;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Slf4j
public class MyAbstractExcelDetailEventExecutor extends AbstractExcelDetailEventExecutor<Document> {

    private GenericListener<List<Document>, HttpServletResponse> listener;

    public MyAbstractExcelDetailEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    public MyAbstractExcelDetailEventExecutor(GenericListener<List<Document>, HttpServletResponse> listener) {
        this(ExcelExecuteStrategy.CONTINUE);
        this.listener = listener;
    }

    @Override
    protected boolean checkTableData(Document data) {
        if (Integer.valueOf(data.getId()) % 3 == 0) {
            return false;
        }
        return true;
    }

    @Override
    protected void doAction(Document data, AnalysisContext context) throws Exception {
        if (context.getCurrentRowNum() % 5 == 0) {
            throw new IllegalArgumentException("数据错误");
        } else {
            System.out.println(data);
        }
        Thread.sleep(200L);
    }

    @Override
    protected void doAfterAll(AnalysisContext context) {
        List<Document> resultList = new ArrayList<>();
        System.out.println("----------------");
        System.out.println("----- 结 束 -----");
        System.out.println("----------------");
        System.out.println("错误数据");
        if (getErrorDataList() != null) {
            getErrorDataList().forEach(System.out::println);
            resultList.addAll(getErrorDataList());

        }
        System.out.println("异常数据");
        if (getErrorDataList() != null) {
            getExceptionDataList().forEach(System.out::println);
            resultList.addAll(getExceptionDataList());
        }
        listener.callback(resultList);
    }

    @Override
    protected boolean checkTableTitle(Map<Integer, String> headMap, AnalysisContext context) {
        return true;
    }

}
