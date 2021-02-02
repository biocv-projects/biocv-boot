package com.biocv.boot.auth.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 *
 * @author Tyler.Feng
 * @date 2021-01-12 10:46
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "AUTH_PERMISSION")
public class AuthPermission {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * 权限名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 权限编码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 权限类型
     *         MODULE：模块
     *          MENU:菜单
     *              API:api接口权限 (真实的权限)
     *              WIDGET:控件 (parent)
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * api 请求方法 GET POST PUT DELETE
     */
    @Column(name = "METHOD")
    private String method;

    /**
     * api 请求路径
     */
    @Column(name = "URI")
    private String uri;

    /**
     * 所属权限
     */
    @ManyToOne
    @JoinColumn(name = "AUTH_PERMISSION_PARENT_ID")
    private AuthPermission parent;

    /**关联表  多个权限 对 多个角色*/
    @ManyToMany(targetEntity = AuthRole.class)
    @JoinTable(name="AUTH_ROLE_PERMISSION",
            joinColumns=@JoinColumn(name="AUTH_PERMISSION_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="AUTH_ROLE_ID",referencedColumnName="ID"))
    private Set<AuthRole> roles = new HashSet<>();
}
