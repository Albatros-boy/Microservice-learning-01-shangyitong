package com.chy.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/2/12 0012 - 02 - 12 - 0:12
 * @Description: com.chy.yygh
 * @version: 1.0
 */


@SpringBootApplication
@ComponentScan(basePackages = "com.chy")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.chy")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
