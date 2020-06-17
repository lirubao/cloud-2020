package com.ozixue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.ozixue.mapper")
@SpringBootApplication
@EnableDiscoveryClient // 该注解用于向consul或者zookeeper作为注册中心时的注册服务
public class ProviderPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApplication.class, args);
    }
}
