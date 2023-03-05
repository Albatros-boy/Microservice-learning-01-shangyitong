package com.chy.yygh.hosp.controller;

import com.chy.yygh.common.result.Result;
import com.chy.yygh.hosp.service.DepartmentService;
import com.chy.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/3/2 0002 - 03 - 02 - 20:48
 * @Description: com.chy.yygh.hosp.controller
 * @version: 1.0
 */

@RequestMapping("/admin/hosp/department")
@CrossOrigin
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}