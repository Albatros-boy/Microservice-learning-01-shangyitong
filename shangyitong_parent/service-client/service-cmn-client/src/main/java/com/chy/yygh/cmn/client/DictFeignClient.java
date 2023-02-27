package com.chy.yygh.cmn.client;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/27 0027 - 02 - 27 - 21:26
 * @Description: com.chy.yygh.cmn.client
 * @version: 1.0
 */


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 数据字典API接口
 * @author hp
 */
@FeignClient("service-cmn")
@Repository
public interface DictFeignClient {

    /**
     * 获取数据字典名称
     * @param dictCode
     * @param value
     * @return
     */
    @ApiOperation(value = "获取数据字典名称")
    @GetMapping(value = "/admin/cmn/dict/getName/{dictCode}/{value}")
    public String getName(
            @ApiParam(name = "dictCode", value = "上级编码", required = true)
            @PathVariable("dictCode") String dictCode,

            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value);
    /**
     * 获取数据字典名称
     * @param value
     * @return
     */
    @ApiOperation(value = "获取数据字典名称")
    @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/admin/cmn/dict/getName/{value}")
    public String getName(
            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value);
}

