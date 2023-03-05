package com.chy.yygh.hosp.service;


import com.chy.yygh.model.hosp.Department;
import com.chy.yygh.vo.hosp.DepartmentQueryVo;
import com.chy.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/21 0021 - 02 - 21 - 0:01
 * @Description: com.chy.yygh.hosp.service
 * @version: 1.0
 */
public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);

    List<DepartmentVo> findDeptTree(String hoscode);

    String getDepName(String hoscode, String depcode);

}
