package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.draper.dboot.system.entity.beans.Document;

/**
 * @author draper_hxy
 */
public class MyAbstractExcelSimpleEventExecutor extends AbstractExcelSimpleEventExecutor<Document> {

    public MyAbstractExcelSimpleEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    @Override
    protected void doAction(Document data, AnalysisContext context) throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println(data);
    }
}
