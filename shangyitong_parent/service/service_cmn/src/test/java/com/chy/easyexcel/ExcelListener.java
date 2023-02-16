package com.chy.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/17 0017 - 02 - 17 - 0:26
 * @Description: com.chy.easyexcel
 * @version: 1.0
 */
public class ExcelListener extends AnalysisEventListener {
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println(o);
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
