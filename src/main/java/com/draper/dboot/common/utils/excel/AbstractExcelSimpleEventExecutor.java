package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import lombok.extern.slf4j.Slf4j;


/**
 * @author draper_hxy
 */
@Slf4j
public abstract class AbstractExcelSimpleEventExecutor<T> extends AbstractExcelEventExecutor<T> {

    public AbstractExcelSimpleEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    @Override
    protected boolean checkTableTitle(T data) {
        return true;
    }

    @Override
    protected boolean checkTableData(T data) {
        return true;
    }

    @Override
    protected void onErrorData(T data, AnalysisContext context) {
    }

    @Override
    protected abstract void doAction(T data, AnalysisContext context) throws Exception;

    @Override
    protected void onFailed(T data, AnalysisContext context, Throwable throwable) {
    }

    @Override
    protected void doAfterAll(AnalysisContext context) {
    }

}
