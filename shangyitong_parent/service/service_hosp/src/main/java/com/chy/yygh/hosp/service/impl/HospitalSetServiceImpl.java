package com.chy.yygh.hosp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chy.yygh.hosp.mapper.HospitalSetMapper;
import com.chy.yygh.hosp.service.HospitalSetService;
import com.chy.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/12 0012 - 02 - 12 - 0:36
 * @Description: com.chy.yygh.hosp.service.impl
 * @version: 1.0
 */

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }



}

