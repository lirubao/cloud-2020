package com.ozixue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTmplateConfig {

    @Bean
    // @LoadBalanced // 使用负载均衡;使服务可以直接到Eureka中拿到服务名称
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
