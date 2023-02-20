package com.chy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chy.yygh.hosp.repository.DepartmentRepository;
import com.chy.yygh.hosp.service.DepartmentService;
import com.chy.yygh.model.hosp.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/21 0021 - 02 - 21 - 0:02
 * @Description: com.chy.yygh.hosp.service.impl
 * @version: 1.0
 */



@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String paramMapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapString, Department.class);

        Department departmentExist =  departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());

        if (departmentExist != null) {
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }
}

