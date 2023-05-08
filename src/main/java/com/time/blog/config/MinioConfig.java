package com.time.blog.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfig {

    private String endpoint;
    private String bucketName;
    private String accessKey;
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint, 9000, true)
                .credentials(accessKey, secretKey)
                .build();
    }
}
