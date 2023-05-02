package com.time.blog.service;

import com.time.blog.domain.dto.TokenDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.reslut.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mjw
 * @date 2023/4/17
 */
public interface LoginService {

    /**
     * 登录接口
     * @param user user
     * @return response
     */
    TokenDTO login(User user);

    /**
     * 注销登录
     * @return response
     */
    ResponseResult<?> logout(HttpServletRequest request);
}
