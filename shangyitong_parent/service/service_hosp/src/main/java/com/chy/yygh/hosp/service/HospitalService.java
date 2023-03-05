package com.chy.yygh.hosp.service;

import com.chy.yygh.model.hosp.Hospital;
import com.chy.yygh.model.hosp.HospitalSet;
import com.chy.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/19 0019 - 02 - 19 - 18:46
 * @Description: com.chy.yygh.hosp.service
 * @version: 1.0
 */


public interface HospitalService {
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateStatus(String id, Integer status);

    Map<String, Object> getHospById(String id);

    String getHospName(String hoscode);
}
