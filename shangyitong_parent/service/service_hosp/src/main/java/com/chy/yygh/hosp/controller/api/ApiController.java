package com.chy.yygh.hosp.controller.api;

import com.chy.yygh.common.exception.YyghException;
import com.chy.yygh.common.helper.HttpRequestHelper;
import com.chy.yygh.common.result.Result;
import com.chy.yygh.common.result.ResultCodeEnum;
import com.chy.yygh.common.untils.MD5;
import com.chy.yygh.hosp.service.DepartmentService;
import com.chy.yygh.hosp.service.HospitalService;
import com.chy.yygh.hosp.service.HospitalSetService;
import com.chy.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
}
