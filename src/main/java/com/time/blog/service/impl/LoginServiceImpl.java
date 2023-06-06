package com.time.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.time.blog.constant.BaseConstants;
import com.time.blog.domain.dto.TokenDTO;
import com.time.blog.domain.dto.UserDetailDTO;
import com.time.blog.domain.entity.User;
import com.time.blog.domain.entity.UserRole;
import com.time.blog.mapper.UserMapper;
import com.time.blog.mapper.UserRoleMapper;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.LoginService;
import com.time.blog.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mjw
 * @date 2023/4/17
 */
@Slf4j
@Transactional
@Service
public class LoginServiceImpl implements LoginService {

    public static final Integer EXPIRES_IN = 86400;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public TokenDTO login(User user) {
        //使用ProviderManager的AuthenticationManager的authenticate方法进行验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //如果校验失败了
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误！");
        }

        //生成token给前端
        //判断user是否已经有了token信息
        TokenDTO token = redisCache.getCacheObject(user.getUserName());
        if (!Objects.isNull(token)) {
            token.setExpires_in(redisCache.getExpire(user.getUserName()));
            return token;
        }

        String uuid = UUID.randomUUID().toString();
        TokenDTO tokenDTO = TokenDTO.builder()
                .access_token(uuid)
                .expires_in(EXPIRES_IN)
                .build();
        //判断是否存在，并存入存入redis
        UserDetailDTO cacheObject = redisCache.getCacheObject(uuid);
        UserDetailDTO userDetailDTO = (UserDetailDTO) authenticate.getPrincipal();
        if (Objects.isNull(cacheObject)) {
            redisCache.setCacheObject(uuid, userDetailDTO, EXPIRES_IN, TimeUnit.SECONDS);
            //存入userName和uuid的token的映射
            redisCache.setCacheObject(user.getUserName(), tokenDTO, EXPIRES_IN, TimeUnit.SECONDS);
        }
        return tokenDTO;
    }

    @Override
    public ResponseResult<?> logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("principal=>{}", authentication.getPrincipal());
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseResult<>(System.currentTimeMillis(), 500, "当前是匿名用户，无需退出", authentication.getPrincipal());
        }
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        String userName = userDetailDTO.getUser().getUserName();
        String token = request.getHeader("token");
        redisCache.deleteObject(token);
        redisCache.deleteObject(userName);
        return new ResponseResult<>(System.currentTimeMillis(),200, "退出成功", userDetailDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> register(User user) {
        //1.先查询此user_name是否已被注册
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, user.getUserName());
        User selectOne = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (!Objects.isNull(selectOne)) {
            return new ResponseResult<>(System.currentTimeMillis(), 500, "当前用户名已被注册", user);
        }
        //2.初始化数据、存表
        user.setNickName(BaseConstants.Authentication.USER);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        user.setDeleteFlag(BaseConstants.N);
        user.setCreationDate(new Date());
        user.setLastUpdateTime(new Date());
        userMapper.insert(user);
        //3.设置角色信息，默认为user
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(2L);
        userRoleMapper.insert(userRole);
        return new ResponseResult<>(System.currentTimeMillis(),200, "注册成功", user);
    }

    @Override
    public String getAvatar(String token) {
        UserDetailDTO userDetailDTO = redisCache.getCacheObject(token);
        return userDetailDTO.getUser().getAvatar();
    }
}
