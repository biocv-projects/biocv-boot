package com.biocv.boot.auth.domain.model;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户
 *
 * @author Tyler.Feng
 * @date 2021-01-12 10:44
 * @since 1.0.0
 */
@Entity
@Table(name = "AUTH_USER")
public class AuthUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 账户名
     */
    @Column(name = "USER_NAME",unique = true)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * root用户
     */
    @Column(name = "IS_ROOT")
    private Boolean isRoot;

    /**
     * 维护角色列表关系
     */
    @ManyToMany(targetEntity = AuthRole.class,fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USER_ROLE",joinColumns = {@JoinColumn(name = "AUTH_USER_ID",referencedColumnName = "ID")},
               inverseJoinColumns = {@JoinColumn(name = "AUTH_ROLE_ID",referencedColumnName = "ID")})
    private Set<AuthRole> authRoleSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AuthRole> getAuthRoleSet() {
        return authRoleSet;
    }

    public void setAuthRoleSet(Set<AuthRole> authRoleSet) {
        this.authRoleSet = authRoleSet;
    }

    public void setIsRoot(Boolean isRoot){
        this.isRoot = isRoot;
    }

    public Boolean getIsRoot(){
        return isRoot;
    }
}
