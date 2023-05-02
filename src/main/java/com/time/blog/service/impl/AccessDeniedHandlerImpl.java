package com.time.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //返回给前端的json
        ResponseResult<Object> responseResult = ResponseResult.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.FORBIDDEN.value())
                .message("您的权限不足！")
                .data(null).build();
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
