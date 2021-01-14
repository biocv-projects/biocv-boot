package com.biocv.boot.autoconfigure.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * 用户
 *
 * @author Tyler.Feng
 * @date 2021-01-12 10:44
 * @since 1.0.0
 */
@Getter
@Setter
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

}
