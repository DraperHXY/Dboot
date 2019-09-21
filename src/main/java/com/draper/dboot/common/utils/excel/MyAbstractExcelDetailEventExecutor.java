package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.draper.dboot.system.entity.beans.Document;

/**
 * @author draper_hxy
 */
public class MyAbstractExcelDetailEventExecutor extends AbstractExcelDetailEventExecutor<Document> {

    public MyAbstractExcelDetailEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    @Override
    protected boolean checkTableTitle(Document data) {
        return true;
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
        Thread.sleep(1000L);
    }

    @Override
    protected void doAfterAll(AnalysisContext context) {
        System.out.println("----------------");
        System.out.println("----- 结 束 -----");
        System.out.println("----------------");
        System.out.println("错误数据");
        getErrorDataList().forEach(System.out::println);
        System.out.println("异常数据");
        getExceptionDataList().forEach(System.out::println);
    }

    @Override
    protected void onErrorData(Document data, AnalysisContext context) {
        super.onErrorData(data, context);
    }

    @Override
    protected void onFailed(Document data, AnalysisContext context, Throwable throwable) {
        super.onFailed(data, context, throwable);
    }
}
