package com.time.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.time.blog.domain.dto.EmailDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.mapper.UserMapper;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.EmailService;
import com.time.blog.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author mjw
 * @date 2023/6/12
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.expire}")
    private String expire;

    private final JavaMailSender mailSender;
    private final RedisCache redisCache;
    private final UserMapper userMapper;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, RedisCache redisCache, UserMapper userMapper) {
        this.mailSender = mailSender;
        this.redisCache = redisCache;
        this.userMapper = userMapper;
    }

    @Override
    public void send(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置发送人
        message.setFrom("时光<" + username + ">");
        // 设置接收人
        message.setTo(emailDTO.getTos().toArray(new String[emailDTO.getTos().size()]));
        // 邮件标题
        message.setSubject(emailDTO.getSubject());
        // 邮件内容
        message.setText(emailDTO.getContent());
        mailSender.send(message);
    }

    @Override
    public ResponseResult<Void> sendEmailCode(String email) {
        // 校验：该邮箱是否被使用
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getEmail, email);
        User selectUser = userMapper.selectOne(queryWrapper);
        if (!Objects.isNull(selectUser)) {
            return new ResponseResult<>(System.currentTimeMillis(), 550, "邮件已被使用", null);
        }
        Object code = redisCache.getCacheObject(email);
        if (code == null) {
            code = String.format("%06d", ThreadLocalRandom.current().nextInt(1000000));
            redisCache.setCacheObject(email, code, Integer.parseInt(expire), TimeUnit.SECONDS);
        }
        EmailDTO emailDTO = EmailDTO.builder()
                .tos(Collections.singletonList(email))
                .subject("确认您的验证码")
                .content("您的验证码为: " + code + "\n" + "有效时间为10分钟。")
                .build();
        try {
            send(emailDTO);
            return new ResponseResult<>(System.currentTimeMillis(), 200, "邮件发送成功", null);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseResult<>(System.currentTimeMillis(), 500, "邮件发送失败", null);
        }
    }

    @Override
    public String checkEmailCode(String email) {
        return redisCache.getCacheObject(email);
    }
}
