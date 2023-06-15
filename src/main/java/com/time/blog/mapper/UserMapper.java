package com.time.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.time.blog.domain.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserByCondition(User user);
}