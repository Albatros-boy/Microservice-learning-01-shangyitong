package com.chy.yygh.hosp.repository;

import com.chy.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/21 0021 - 02 - 21 - 0:00
 * @Description: com.chy.yygh.hosp.repository
 * @version: 1.0
 */


@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}

