package com.time.blog.config;

import com.time.blog.filter.AuthenticationTokenFilter;
import com.time.blog.service.impl.AccessDeniedHandlerImpl;
import com.time.blog.service.impl.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author mjw
 * @date 2023/4/14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //不通过Session获取SecurityContext
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //支持跨域访问
                .cors()
                .and()
                // 开启请求的权限管理
                .authorizeRequests()
                // 对于登录接口，允许匿名访问
                // antMatchers("/api")只允许匹配/api，mvcMatchers("/api")还允许/api.html,/api.xyz等等，并且更安全，推荐使用
                .mvcMatchers("/article/**").permitAll()
                .mvcMatchers("/category/**").permitAll()
                .mvcMatchers("/comments/**").permitAll()
                .mvcMatchers("/minio/**").permitAll()
                // 对于所有/admin的api，默认只有admin权限才可以使用
                .mvcMatchers("/admin/**").hasAuthority("admin")
                //hasAnyRole()是指用户的角色，hasAnyAuthority()是指给不同的角色配置不同的菜单权限
                //anonymous匿名，得登录，但是不限制角色，permitAll，不需要登录就可以访问
                .mvcMatchers("/oauth/**").permitAll()
                // 除上面以外的全部接口 都需要权限验证
                .anyRequest()
                .authenticated()
                .and()
                // 关闭csrf
                .csrf()
                .disable();

        // 处理异常
        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                .accessDeniedHandler(new AccessDeniedHandlerImpl());

        //把token校验添加到过滤器链中
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }
}
