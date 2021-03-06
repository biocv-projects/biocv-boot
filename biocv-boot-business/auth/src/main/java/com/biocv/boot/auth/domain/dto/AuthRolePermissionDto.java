package com.biocv.boot.auth.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * auth permission dto
 * @author tyler.feng
 */
@Data
public class AuthRolePermissionDto implements Serializable {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限列表
     */
    private List<String> permissions = new ArrayList<>();

}
