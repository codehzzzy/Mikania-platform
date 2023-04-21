package com.zjxz.mikaniaplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description Knife4j接口文档配置
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {
    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("MikaniaPlatform")
                        .description("薇甘菊平台")
                        .version("1.0")
                        .build())
                .select()
                // 指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zjxz"))
                .paths(PathSelectors.any())
                .build();
    }
}