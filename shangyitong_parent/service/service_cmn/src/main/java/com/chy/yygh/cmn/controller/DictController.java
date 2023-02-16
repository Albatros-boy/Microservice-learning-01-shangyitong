package com.chy.yygh.cmn.controller;

import com.chy.yygh.cmn.service.DictService;
import com.chy.yygh.common.result.Result;
import com.chy.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin
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

}
