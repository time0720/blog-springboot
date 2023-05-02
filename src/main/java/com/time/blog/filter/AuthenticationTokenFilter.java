package com.time.blog.filter;

import com.time.blog.domain.dto.UserDetailDTO;
import com.time.blog.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/4/17
 */
@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取header中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //如果没有token，则放心，让后面其他的过滤器执行
            filterChain.doFilter(request, response);
            return;
        }
        log.info("token--->{}", token);

        //3.redis获取用户信息
        UserDetailDTO userDetailDTO = redisCache.getCacheObject(token);
        log.info("userDetailDTO--->{}", userDetailDTO);

        if (Objects.isNull(userDetailDTO)) {
            filterChain.doFilter(request, response);
            return;
        }
        //4封装Authentication
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetailDTO, null, userDetailDTO.getAuthorities());

        //5存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }
}
