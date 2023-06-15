package com.time.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.User;
import com.time.blog.mapper.UserMapper;
import com.time.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/15
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public PageInfo<User> selectUserByCondition(int pageNum, int pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectUserByCondition(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
