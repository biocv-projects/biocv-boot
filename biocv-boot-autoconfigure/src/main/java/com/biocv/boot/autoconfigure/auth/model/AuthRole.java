package com.biocv.boot.autoconfigure.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 角色
 *
 * @author Tyler.Feng
 * @date 2021-01-12 10:46
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "AUTH_ROLE")
public class AuthRole {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * 角色名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 角色编码
     */
    @Column(name = "CODE",unique = true)
    private String code;

    /**
     * 权限列表
     */
    @ManyToMany(targetEntity = AuthPermission.class,fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_ROLE_PERMISSION",
                joinColumns = {@JoinColumn(name = "AUTH_ROLE_ID",referencedColumnName = "ID")},
                inverseJoinColumns = @JoinColumn(name = "AUTH_PERMISSION_ID",referencedColumnName = "ID"))
    private Set<AuthPermission> authPermissionSet;

    /**
     * 用户列表
     */
    @ManyToMany(targetEntity = AuthUser.class)
    @JoinTable(name="AUTH_USER_ROLE",
            joinColumns=@JoinColumn(name="AUTH_ROLE_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="AUTH_USER_ID",referencedColumnName="ID"))
    private Set<AuthUser> users = new HashSet<>();

}
