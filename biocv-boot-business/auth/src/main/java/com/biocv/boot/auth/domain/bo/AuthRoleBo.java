package com.biocv.boot.auth.domain.bo;

import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.auth.domain.model.AuthUser;
import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Auth Role bo
 *
 * @author kai
 * @date 2021/2/2 10:21
 */
public class AuthRoleBo extends BaseBo {

    /**
     * 角色名称
     */
    @QueryCondition(column = "name")
    private String name;

    /**
     * 角色编码
     */
    @QueryCondition(column = "code")
    private String code;

    /**
     * 权限列表
     */
    private Set<String> authPermissionIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<String> getAuthPermissionIds() {
        return authPermissionIds;
    }

    public void setAuthPermissionIds(Set<String> authPermissionIds) {
        this.authPermissionIds = authPermissionIds;
    }
}
