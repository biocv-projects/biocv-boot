package com.biocv.boot.auth.domain.vo;

import com.biocv.boot.pojo.BaseVo;

/**
 * 用户 vue展示vo
 *
 * @author kai
 * @date 2021/2/2 15:22
 */
public class AuthUserVo extends BaseVo {

    private String name;

    /**
     * 账户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * root用户
     */
    private Boolean isRoot;

    /**
     * 角色名称，用","隔开
     */
    private String roleNames;

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

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
}
