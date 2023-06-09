package com.time.blog.controller;

import com.time.blog.aop.OperationLogAnnotation;
import com.time.blog.domain.dto.TokenDTO;
import com.time.blog.domain.dto.UserDetailDTO;
import com.time.blog.domain.dto.UserUpdateDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mjw
 * @date 2023/4/27
 */
@RestController
@RequestMapping("/oauth")
@Api(tags = "登录管理")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @OperationLogAnnotation(operModelCode = "LOGIN", operType = "登录", operDesc = "登录")
    @ApiOperation("登录")
    @PostMapping("/login")
    public TokenDTO login(@RequestBody User user) {
        return loginService.login(user);
    }

    @OperationLogAnnotation(operModelCode = "LOGIN", operType = "注销", operDesc = "注销")
    @ApiOperation("注销")
    @PostMapping("/logout")
    public ResponseResult<?> logout(HttpServletRequest request){
        return loginService.logout(request);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseResult<?> register(@RequestBody User user) {
       return loginService.register(user);
    }

    @ApiOperation("根据token值获取当前登录的用户")
    @GetMapping("/getUserInfo")
    public UserDetailDTO getUserInfo(String token) {
        return loginService.getUserInfo(token);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public ResponseResult<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return loginService.updateUser(userUpdateDTO);
    }
}
