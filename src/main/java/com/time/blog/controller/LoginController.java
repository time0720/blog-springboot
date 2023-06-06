package com.time.blog.controller;

import com.time.blog.aop.OperationLogAnnotation;
import com.time.blog.domain.dto.TokenDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
