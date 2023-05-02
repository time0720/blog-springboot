package com.time.blog.utils;

import com.time.blog.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Component
@Slf4j
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;

    /**
     * 上传文件并返回文件的url
     *
     * @param file file
     * @return fileUrl
     */
    public String upload(MultipartFile file) {
        // 使用putObject上传一个文件到存储桶中。
        try {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            log.info("fileName->{}", fileName);
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            return minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
