package com.chy.yygh.hosp.repository;

import com.chy.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/19 0019 - 02 - 19 - 18:45
 * @Description: com.chy.yygh.hosp.repository
 * @version: 1.0
 */


@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    //判断是否存在数据
    Hospital getHosptialByHoscode(String hoscode);
}

