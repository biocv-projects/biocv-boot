package com.biocv.boot.autoconfigure.security.userDetails;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * 用户
 *
 * @author kai
 * @date 2020/10/24 15:16
 */
public class UserInfo implements UserDetails {

    /**
     * 用户名
     */
    @Setter
    private String userName;

    /**
     * 密码
     */
    @Setter
    private String password;

    /**
     * 角色字符串列表
     */
    private HashSet<String> roleSet;

    /**
     * 权限列表
     */
    @Setter
    private Collection<Autoriztion> authorities;

    /**
     * 是否过期
     */
    @Setter
    private boolean isAccountNonExpired = true;

    /**
     * 是否被锁定
     */
    @Setter
    private boolean isAccountNonLocked = true;

    /**
     * 启用状态
     */
    @Setter
    private boolean enabled = true;

    /**
     * Getter
     * @return
     */
    public HashSet<String> getRoleSet() {
        return roleSet;
    }

    /**
     * Setter
     * @param roleSet
     */
    public void setRoleSet(HashSet<String> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 权限
     */
    public static class Autoriztion implements GrantedAuthority{

        @Setter
        private String authorizition;

        @Override
        public String getAuthority() {
            return authorizition;
        }
    }
}
