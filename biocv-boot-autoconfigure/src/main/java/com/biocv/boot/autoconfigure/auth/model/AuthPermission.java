package com.biocv.boot.autoconfigure.auth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

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
    @GeneratedValue
    @Column(name = "ID", length = 50, nullable = false)
    private UUID id;

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
     * 所属权限
     */
    @ManyToOne
    @JoinColumn(name = "AUTH_PERMISSION_PARENT_ID")
    private AuthPermission parent;

}
