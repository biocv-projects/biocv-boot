package com.biocv.boot.auth.domain.dto;

import com.biocv.boot.pojo.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 权限数据传输
 *
 * @author Tyler.Feng
 * @date 2021-01-14 11:39
 * @since 1.0.0
 */
@Data
public class AuthRoleDto extends BaseDto implements Serializable {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 权限列表
     */
    private Set<String> permissionSet;
}
