package com.zjxz.mikaniaplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjxz.mikaniaplatform.mapper")
public class MikaniaPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(MikaniaPlatformApplication.class, args);
    }

}
