package com.chy.yygh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Auther: cuihongyuan
 * @Date: 2023/3/5 0005 - 03 - 05 - 22:03
 * @Description: com.chy.yygh
 * @version: 1.0
 */


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ServerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerGatewayApplication.class, args);
    }
}
