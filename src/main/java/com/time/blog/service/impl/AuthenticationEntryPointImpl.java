package com.time.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mjw
 * @date 2022/12/5
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //返回给前端的json
        ResponseResult<Object> responseResult = ResponseResult.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("用户名或密码错误，登录失败！")
                .data(null).build();
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
