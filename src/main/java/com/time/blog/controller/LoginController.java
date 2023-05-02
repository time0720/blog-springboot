package com.time.blog.controller;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.dto.TokenDTO;
import com.time.blog.domain.entity.Article;
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

    @ApiOperation("登录")
    @PostMapping("/login")
    public TokenDTO login(@RequestBody User user) {
        return loginService.login(user);
    }

    @ApiOperation("注销")
    @PostMapping("/logout")
    public ResponseResult<?> logout(HttpServletRequest request){
        loginService.logout(request);
        return ResponseResult.success();
    }
}
