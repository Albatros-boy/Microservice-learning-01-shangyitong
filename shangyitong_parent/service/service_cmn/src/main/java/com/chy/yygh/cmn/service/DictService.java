package com.chy.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chy.yygh.model.cmn.Dict;
import com.chy.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/12 0012 - 02 - 12 - 0:35
 * @Description: com.chy.yygh.hosp.service
 * @version: 1.0
 */

public interface DictService extends IService<Dict> {
    List<Dict> findChlidData(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

    String getDictName(String dictCodes, String value);

    List<Dict> findByDictCode(String dictCode);
}
