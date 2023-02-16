package com.chy.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chy.yygh.cmn.mapper.DictMapper;
import com.chy.yygh.model.cmn.Dict;
import org.springframework.beans.BeanUtils;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/17 0017 - 02 - 17 - 1:00
 * @Description: com.chy.yygh.cmn.listener
 * @version: 1.0
 */

public class DictListener extends AnalysisEventListener {

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行读取
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(o,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
