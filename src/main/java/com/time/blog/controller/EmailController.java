package com.time.blog.controller;

import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjw
 * @date 2023/6/12
 */
@RestController
@Slf4j
@Api(tags = "邮箱管理")
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ApiOperation("发送验证码邮件")
    @PostMapping("/sendEmailCode")
    public ResponseResult<Void> sendEmail(String email) {
        return emailService.sendEmailCode(email);
    }

    @ApiOperation("查看邮件的验证码")
    @GetMapping("/checkEmailCode")
    public String checkEmailCode(String email) {
        return emailService.checkEmailCode(email);
    }
}
