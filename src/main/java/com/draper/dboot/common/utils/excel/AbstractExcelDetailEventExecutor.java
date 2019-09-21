package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 注意不要覆写无关的方法，否则可能导致框架失效
 * 待重构
 *
 * @author draper_hxy
 */
public abstract class AbstractExcelDetailEventExecutor<T> extends AbstractExcelEventExecutor<T> {

    private List<T> errorDataList = null;

    private List<T> exceptionDataList = null;

    public AbstractExcelDetailEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    @Override
    protected void onErrorData(T data, AnalysisContext context) {
        if (errorDataList == null) {
            errorDataList = new ArrayList<>();
        }
        errorDataList.add(data);
    }

    @Override
    protected abstract void doAction(T data, AnalysisContext context) throws Exception;

    @Override
    protected void onFailed(T data, AnalysisContext context, Throwable throwable) {
        if (exceptionDataList == null) {
            exceptionDataList = new ArrayList<>();
        }
        exceptionDataList.add(data);
    }

    @Override
    protected abstract void doAfterAll(AnalysisContext context);

    public List<T> getErrorDataList() {
        return errorDataList;
    }

    public List<T> getExceptionDataList() {
        return exceptionDataList;
    }

    @Override
    protected abstract boolean checkTableTitle(Map<Integer, String> headMap, AnalysisContext context);
}
