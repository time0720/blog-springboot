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

    /**
     * 用户注册
     *
     * @param user user
     * @return response
     */
    ResponseResult<?> register(User user);

    /**
     * 根据token值获取用户头像
     * @param token token
     * @return avatar
     */
    String getAvatar(String token);
}
