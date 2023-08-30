package com.mk.servicego.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guocz
 * @date 2023/8/29 16:28
 * 加载minio开头的配置项
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private Integer port;

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        // 把官方提供的MinioClient客户端注册到IOC容器中
        return new MinioClient(getEndpoint(), getPort(), getAccessKey(), getSecretKey(), false);
    }
}
