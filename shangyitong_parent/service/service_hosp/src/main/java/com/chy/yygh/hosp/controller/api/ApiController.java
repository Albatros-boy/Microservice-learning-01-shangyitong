package com.chy.yygh.hosp.controller.api;


import com.chy.yygh.common.exception.YyghException;
import com.chy.yygh.common.helper.HttpRequestHelper;
import com.chy.yygh.common.result.Result;
import com.chy.yygh.common.result.ResultCodeEnum;
import com.chy.yygh.common.untils.MD5;
import com.chy.yygh.hosp.service.DepartmentService;
import com.chy.yygh.hosp.service.HospitalService;
import com.chy.yygh.hosp.service.HospitalSetService;
import com.chy.yygh.hosp.service.ScheduleService;
import com.chy.yygh.model.hosp.Department;
import com.chy.yygh.model.hosp.Hospital;
import com.chy.yygh.model.hosp.Schedule;
import com.chy.yygh.vo.hosp.DepartmentQueryVo;
import com.chy.yygh.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hp
 * @Auther: cuihongyuan
 * @Date: 2023/2/19 0019 - 02 - 19 - 20:36
 * @Description: com.chy.yygh.hosp.controller.api
 * @version: 1.0
 */

@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;


    @ApiOperation(value = "上传医院信息")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String hoscode  = (String) paramMap.get("hoscode");
        String signKey =  hospitalSetService.getSignKey(hoscode);

        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        hospitalService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取医院信息")
    @PostMapping("hospital/show")
    public Result gethospital(HttpServletRequest request) {

        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        departmentService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取分页列表")
    @PostMapping("department/list")
    public Result department(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");

        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }

        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除科室")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
        //必填
        String depcode = (String)paramMap.get("depcode");

        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }

    @ApiOperation(value = "上传排班")
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hoscode = (String)paramMap.get("hoscode");
        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        scheduleService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取排班分页列表")
    @PostMapping("schedule/list")
    public Result schedule(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");

        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }


        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page , limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }


    @ApiOperation(value = "删除科室")
    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hoscode = (String)paramMap.get("hoscode");
        String hosScheduleId = (String)paramMap.get("hosScheduleId");
        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");

        //根据传过来的医院编码查询数据库查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //数据库的查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }

}
