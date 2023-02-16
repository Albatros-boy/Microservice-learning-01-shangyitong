package com.chy.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/17 0017 - 02 - 17 - 0:14
 * @Description: com.chy.easyexcel
 * @version: 1.0
 */


public class TestWrite {
    public static void main(String[] args) {

        ArrayList<UserData> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("lucy" + 1);
            list.add(data);
        }

        String fileName = "E:\\崔洪源\\1.java\\项目\\代码\\Microservice-learning-01-shangyitong\\shangyitong_parent\\service\\service_cmn\\excel\\01.xlsx";

        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);
    }
}
