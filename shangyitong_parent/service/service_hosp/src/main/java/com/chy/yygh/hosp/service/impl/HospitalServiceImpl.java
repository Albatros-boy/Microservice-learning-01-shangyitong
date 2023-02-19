package com.chy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chy.yygh.hosp.repository.HospitalRepository;
import com.chy.yygh.hosp.service.HospitalService;
import com.chy.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/19 0019 - 02 - 19 - 18:47
 * @Description: com.chy.yygh.hosp.service.impl
 * @version: 1.0
 */

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {

        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        //是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist =  hospitalRepository.getHosptialByHoscode(hoscode);

        if (hospitalExist != null) {
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }


    }
}
