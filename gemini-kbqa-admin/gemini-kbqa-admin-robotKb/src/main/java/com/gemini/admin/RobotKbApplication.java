package com.gemini.admin;

import com.gemini.admin.ws.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: XXY
 * @Date: 2021/2/19 19:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.gemini.admin.dao.mapper")
@EnableTransactionManagement
public class RobotKbApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobotKbApplication.class, args);
        try {
            new NettyServer(8005).start();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
