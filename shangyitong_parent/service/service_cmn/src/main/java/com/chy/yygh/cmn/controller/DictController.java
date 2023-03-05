package com.chy.yygh.cmn.controller;

import com.chy.yygh.cmn.service.DictService;
import com.chy.yygh.common.result.Result;
import com.chy.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/16 0016 - 02 - 16 - 21:15
 * @Description: com.chy.yygh.cmn.controller
 * @version: 1.0
 */

@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
//@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }

    @ApiOperation(value="导出")
    @GetMapping(value = "exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    @ApiOperation(value="导入")
    @PostMapping(value = "importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }

    @ApiOperation(value = "获取数据字典名称")
    @GetMapping(value = "/getName/{dictCode}/{value}")
    public String getName(
            @ApiParam(name = "dictCode", value = "上级编码", required = true)
            @PathVariable("dictCode") String dictCode,

            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {
        return dictService.getDictName(dictCode, value);
    }

    @ApiOperation(value = "获取数据字典名称")
    @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/getName/{value}")
    public String getName(
            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {
        return dictService.getDictName("", value);
    }

    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping(value = "/findByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(
            @ApiParam(name = "dictCode", value = "节点编码", required = true)
            @PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

}
