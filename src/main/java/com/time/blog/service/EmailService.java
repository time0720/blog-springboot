package com.time.blog.service;

import com.time.blog.domain.dto.EmailDTO;
import com.time.blog.reslut.ResponseResult;

/**
 * @author mjw
 * @date 2023/6/12
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailDTO emailDTO邮件列表
     */
    void send(EmailDTO emailDTO);

    /**
     * 发送验证码邮件
     * @param email 目标email
     * @return void
     */
    ResponseResult<Void> sendEmailCode(String email);

    /**
     * 查看邮件的验证码
     * @param email email
     * @return code
     */
    String checkEmailCode(String email);

}
