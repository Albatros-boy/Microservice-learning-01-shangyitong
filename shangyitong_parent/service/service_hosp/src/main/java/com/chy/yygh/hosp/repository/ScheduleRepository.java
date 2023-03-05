package com.chy.yygh.hosp.repository;

import com.chy.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/22 0022 - 02 - 22 - 0:58
 * @Description: com.chy.yygh.hosp.repository
 * @version: 1.0
 */


@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}

