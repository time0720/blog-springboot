package com.time.blog.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.time.blog.constant.BaseConstants;
import com.time.blog.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mjw
 * @date 2023/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO implements UserDetails {

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 用户权限
     */
    List<String> permissions;

    public UserDetailDTO(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    List<SimpleGrantedAuthority> authorities;

    /**
     * 将数据库中用户的权限，转为UserDetails中的authorities
     * @return authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 帐户未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return BaseConstants.TRUE;
    }

    /**
     * 帐户未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return BaseConstants.TRUE;
    }

    /**
     * 证书未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return BaseConstants.TRUE;
    }

    /**
     * 是否启用
     */
    @Override
    public boolean isEnabled() {
        return BaseConstants.TRUE;
    }
}
