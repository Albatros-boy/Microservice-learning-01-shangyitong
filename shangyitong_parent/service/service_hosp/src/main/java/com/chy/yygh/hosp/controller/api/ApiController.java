package com.chy.yygh.hosp.controller.api;

import com.chy.yygh.common.helper.HttpRequestHelper;
import com.chy.yygh.common.result.Result;
import com.chy.yygh.hosp.service.HospitalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
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

    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
