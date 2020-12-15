package com.gemini.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: XXY
 * @Date: 2020/12/14 0:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CustomerKbApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerKbApplication.class, args);
    }
}
