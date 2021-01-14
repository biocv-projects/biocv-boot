package com.biocv.boot.autoconfigure.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthRolePermissionDto implements Serializable {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限列表
     */
    private List<String> permissions;

}
