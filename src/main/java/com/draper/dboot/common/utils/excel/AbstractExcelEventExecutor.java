package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author draper_hxy
 */
@Slf4j
@Data
@SuppressWarnings("all")
public abstract class AbstractExcelEventExecutor<T> extends AnalysisEventListener<T> {


    private ExcelExecuteStrategy strategy;

    public AbstractExcelEventExecutor(ExcelExecuteStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (context.getCurrentRowNum() == 0) {
            if (checkTableTitle(data)) {
                log.debug("Excel 上传中，表头正确");
            } else {
                log.error("Excel 表头错误，data = {}", data);
                context.interrupt();
            }
        } else {
            if (!checkTableData(data)) {
                onErrorData(data, context);
                if (strategy == ExcelExecuteStrategy.ABORT) {
                    log.error("数据错误，终止操作，data = {}", data);
                    context.interrupt();
                } else if (strategy == ExcelExecuteStrategy.CONTINUE) {
                    log.warn("数据错误，跳过操作，data = {}", data);
                    return;
                } else {
                    throw new RuntimeException("不支持的解析策略");
                }
            }

            try {
                doAction(data, context);
                // 上传时发生异常
            } catch (Exception e) {
                onFailed(data, context, e);
                if (strategy == ExcelExecuteStrategy.ABORT) {
                    log.error("操作失败，终止操作，data = {}", data);
                    context.interrupt();
                } else if (strategy == ExcelExecuteStrategy.CONTINUE) {
                    log.warn("操作失败，跳过操作，data = {}", data);
                } else {
                    throw new RuntimeException("不支持的解析策略");
                }
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 行数不包括表头
        log.info("操作结束，数据行数 = {}", context.getCurrentRowNum());
        doAfterAll(context);
    }

    /**
     * 检查表头
     */
    protected abstract boolean checkTableTitle(T t);

    /**
     * 检查数据
     */
    protected abstract boolean checkTableData(T t);

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
    protected abstract void onFailed(T data, AnalysisContext context, Throwable throwable);

    /**
     * 当所有业务结束后，进行处理
     */
    protected abstract void doAfterAll(AnalysisContext context);

}
