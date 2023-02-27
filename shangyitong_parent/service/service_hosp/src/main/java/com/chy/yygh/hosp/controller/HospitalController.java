package com.chy.yygh.hosp.controller;

import com.chy.yygh.common.result.Result;
import com.chy.yygh.hosp.service.HospitalService;
import com.chy.yygh.model.hosp.Hospital;
import com.chy.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/22 0022 - 02 - 22 - 22:21
 * @Description: com.chy.yygh.hosp.controller
 * @version: 1.0
 */
@Api(tags = "医院管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,

            @ApiParam(name = "hospitalQueryVo", value = "查询对象", required = false)
                    HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel =  hospitalService.selectPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

}
