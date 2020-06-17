package com.ozixue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启Swagger2
public class SwaggerConfig {

    // 配置Swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // enable: 默认为true, 如果为false,则Swagger不能在再浏览器上访问了
                // .enable(true)
                .select()
                // RequestHandlerSelectors: 配置要扫描接口的方式
                // basePackage: 指定要扫描的包
                // any(): 扫描全部
                .apis(RequestHandlerSelectors.basePackage("com.ozixue.controller"))
                // 过滤指扫描什么路径
                .paths(PathSelectors.any())
                .build();
    }

    // 配置Swagger相关信息
    private ApiInfo apiInfo() {
        return new ApiInfo("Cloud WEB API",
                "Api Documentation",
                "1.0",
                "http://docs.ozixue.com",
                ApiInfo.DEFAULT_CONTACT,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
