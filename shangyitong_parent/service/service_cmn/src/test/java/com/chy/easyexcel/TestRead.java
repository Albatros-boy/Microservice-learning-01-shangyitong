package com.chy.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/17 0017 - 02 - 17 - 0:30
 * @Description: com.chy.easyexcel
 * @version: 1.0
 */


public class TestRead {

    public static void main(String[] args) {
        String fileName = "E:\\崔洪源\\1.java\\项目\\代码\\Microservice-learning-01-shangyitong\\shangyitong_parent\\service\\service_cmn\\excel\\01.xlsx";
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
