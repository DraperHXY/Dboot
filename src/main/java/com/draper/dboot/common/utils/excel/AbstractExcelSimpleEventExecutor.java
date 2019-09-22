package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


/**
 * @author draper_hxy
 */
@Slf4j
public abstract class AbstractExcelSimpleEventExecutor<T> extends AbstractExcelEventExecutor<T> {

    public AbstractExcelSimpleEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    @Override
    protected boolean checkTableTitle(Map<Integer, String> headMap, AnalysisContext context) {
        return true;
    }

    @Override
    protected boolean checkTableData(T t, AnalysisContext context) {
        return true;
    }

    @Override
    protected void onErrorData(T data, AnalysisContext context) {
    }

    @Override
    protected abstract void doAction(T data, AnalysisContext context) throws Exception;

    @Override
    protected void onActionFailed(T data, AnalysisContext context, Throwable throwable) {
    }

    @Override
    protected void doAfterAll(AnalysisContext context) {
    }

}
