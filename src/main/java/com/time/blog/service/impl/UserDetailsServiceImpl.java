package com.time.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.time.blog.domain.dto.UserDetailDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.mapper.MenuMapper;
import com.time.blog.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/4/17
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名错误");
        }
        List<String> permissions = menuMapper.selectPermsByUserId(user.getUserId());
        UserDetailDTO userDetailDTO = new UserDetailDTO(user, permissions);
        System.out.println(userDetailDTO);
        //封装成UserDetails对象返回
        return userDetailDTO;
    }
}
