package com.chy.yygh.hosp.service;

import com.chy.yygh.model.hosp.Schedule;
import com.chy.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/22 0022 - 02 - 22 - 1:16
 * @Description: com.chy.yygh.hosp.service
 * @version: 1.0
 */
public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
