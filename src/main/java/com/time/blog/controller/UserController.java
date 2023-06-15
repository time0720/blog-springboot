package com.time.blog.controller;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.User;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjw
 * @date 2023/6/15
 */
@RestController
@Api(tags = "用户管理")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("根据条件查询用户")
    @GetMapping("/user/selectUser")
    public ResponseResult<PageInfo<User>> selectCommentsByCondition(int pageNum, int pageSize, User user) {
        return ResponseResult.success(userService.selectUserByCondition(pageNum, pageSize, user));
    }

}
