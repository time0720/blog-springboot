package com.time.blog.controller;

import com.time.blog.reslut.ResponseResult;
import com.time.blog.utils.MinioUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Api("minio操作")
@RestController
@RequestMapping("/minio")
@CrossOrigin
@Slf4j
public class MinioController {

    @Autowired
    private MinioUtils minioUtils;


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public ResponseResult<String> upload(@RequestBody MultipartFile file) {
        log.info("file-->{}", file);
        String fileUrl = minioUtils.upload(file);
        return ResponseResult.success(fileUrl);
    }
}
