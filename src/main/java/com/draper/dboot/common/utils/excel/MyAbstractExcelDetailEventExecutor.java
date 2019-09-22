package com.draper.dboot.common.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.draper.dboot.common.utils.GenericListener;
import com.draper.dboot.system.entity.DocumentInfo;
import com.draper.dboot.system.entity.beans.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Slf4j
public class MyAbstractExcelDetailEventExecutor extends AbstractExcelDetailEventExecutor<Document> {

    private GenericListener<List<DocumentInfo>, HttpServletResponse> listener;

    private List<DocumentInfo> errorDataList = new ArrayList<>();
    private List<DocumentInfo> exceptionDataList = new ArrayList<>();

    public MyAbstractExcelDetailEventExecutor(ExcelExecuteStrategy strategy) {
        super(strategy);
    }

    public MyAbstractExcelDetailEventExecutor(GenericListener<List<DocumentInfo>, HttpServletResponse> listener) {
        this(ExcelExecuteStrategy.CONTINUE);
        this.listener = listener;
    }

    @Override
    protected void doAction(Document data, AnalysisContext context) throws Exception {
        if (context.readRowHolder().getRowIndex() % 5 == 0) {
            throw new IllegalArgumentException("数据错误");
        } else {
            System.out.println(data);
        }
        Thread.sleep(200L);
    }

    @Override
    protected void doAfterAll(AnalysisContext context) {
        List<DocumentInfo> resultList = new ArrayList<>();
        System.out.println("----------------");
        System.out.println("----- 结 束 -----");
        System.out.println("----------------");
        System.out.println("错误数据");
        if (errorDataList != null) {
            errorDataList.forEach(System.out::println);
            resultList.addAll(errorDataList);

        }
        System.out.println("异常数据");
        if (exceptionDataList != null) {
            exceptionDataList.forEach(System.out::println);
            resultList.addAll(exceptionDataList);
        }
        listener.callback(resultList);
    }

    @Override
    protected boolean checkTableTitle(Map<Integer, String> headMap, AnalysisContext context) {
        return true;
    }

    @Override
    protected boolean checkTableData(Document document, AnalysisContext context) {
        if (Integer.valueOf(document.getId()) % 2 == 0) {
            return false;
        }
        return true;
    }


    @Override
    protected void onErrorData(Document data, AnalysisContext context) {
        DocumentInfo info = new DocumentInfo();
        BeanUtils.copyProperties(data, info);
        info.setMessage("数据格式错误，请检查");
        errorDataList.add(info);
    }

    @Override
    protected void onActionFailed(Document data, AnalysisContext context, Throwable throwable) {
        DocumentInfo info = new DocumentInfo();
        BeanUtils.copyProperties(data, info);
        info.setMessage(throwable.getMessage());
        errorDataList.add(info);
    }

    @Override
    public List<Document> getErrorDataList() {
        return super.getErrorDataList();
    }
}
