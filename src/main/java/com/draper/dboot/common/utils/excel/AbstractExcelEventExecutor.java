package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author draper_hxy
 */
@Slf4j
@Data
public abstract class AbstractExcelEventExecutor<T> extends AnalysisEventListener<T> {


    private ExcelExecuteStrategy strategy;

    private boolean isInterrupt = false;

    public AbstractExcelEventExecutor(ExcelExecuteStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (!checkTableData(data,context)) {
            onErrorData(data, context);
            if (strategy == ExcelExecuteStrategy.ABORT) {
                context.interrupt();
            } else if (strategy == ExcelExecuteStrategy.CONTINUE) {
                return;
            } else {
                throw new RuntimeException("不支持的解析策略");
            }
        }

        try {
            doAction(data, context);
            // 上传时发生异常
        } catch (Exception e) {
            onActionFailed(data, context, e);
            if (strategy == ExcelExecuteStrategy.ABORT) {
                context.interrupt();
            } else if (strategy == ExcelExecuteStrategy.CONTINUE) {
            } else {
                throw new RuntimeException("不支持的解析策略");
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 行数不包括表头
        log.info("操作结束，数据行数 = {}", context.getCurrentRowNum());
        doAfterAll(context);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        boolean isSuccess = checkTableTitle(headMap, context);
        if (!isSuccess) {
            isInterrupt = true;
            hasNext(context);
        }
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        if (isInterrupt) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查表头
     */
    protected abstract boolean checkTableTitle(Map<Integer, String> headMap, AnalysisContext context);

    /**
     * 检查数据
     */
    protected abstract boolean checkTableData(T t, AnalysisContext context);

    /**
     * 当数据错误，进行处理，
     */
    protected abstract void onErrorData(T data, AnalysisContext context);

    /**
     * 进行业务操作
     */
    protected abstract void doAction(T t, AnalysisContext context) throws Exception;

    /**
     * 当业务发生异常时进行处理
     */
    protected abstract void onActionFailed(T data, AnalysisContext context, Throwable throwable);

    /**
     * 当所有业务结束后，进行处理
     */
    protected abstract void doAfterAll(AnalysisContext context);

}
