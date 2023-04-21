package com.zjxz.mikaniaplatform.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzzzzzy
 * @create 2023/4/2
 * @description oss基础配置类
 */
@Configuration
@Data
public class AliyunConfig {
    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.maxConnections}")
    private Integer maxConnections;

    @Value("${oss.maxErrorRetry}")
    private Integer maxErrorRetry;

    @Value("${oss.idleConnectionTime}")
    private Integer idleConnectionTime;

    @Bean
    public OSS ossClient() {
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setMaxConnections(maxConnections);
        conf.setMaxErrorRetry(maxErrorRetry);
        conf.setMaxErrorRetry(idleConnectionTime);
        return new OSSClientBuilder().build(
                endpoint,
                accessKeyId,
                accessKeySecret,
                conf
        );
    }
}