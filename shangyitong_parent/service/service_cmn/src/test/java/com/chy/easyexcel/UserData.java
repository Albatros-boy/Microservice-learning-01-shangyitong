package com.chy.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/17 0017 - 02 - 17 - 0:11
 * @Description: com.chy.easyexcel
 * @version: 1.0
 */

@Data
public class UserData {

    @ExcelProperty("用户编号")
    private int uid;

    @ExcelProperty("用户名称")
    private String username;
}
