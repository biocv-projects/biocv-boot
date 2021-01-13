package com.biocv.boot.autoconfigure.auth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue
    @Column(name = "ID", length = 50, nullable = false)
    private UUID id;

    /**
     * 角色名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 权限列表
     */
    @ManyToMany(targetEntity = AuthPermission.class)
    @JoinTable(name = "AUTH_ROLE_PERMISSION",
                joinColumns = {@JoinColumn(name = "AUTH_ROLE_ID",referencedColumnName = "ID")},
                inverseJoinColumns = @JoinColumn(name = "AUTH_PERMISSION_ID",referencedColumnName = "ID"))
    private Set<AuthPermission> authPermissionSet;

}
