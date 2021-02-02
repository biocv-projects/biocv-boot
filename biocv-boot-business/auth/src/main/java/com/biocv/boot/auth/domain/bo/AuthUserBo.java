package com.biocv.boot.auth.domain.bo;

import com.biocv.boot.autoconfigure.security.support.IAuthUser;
import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;

import java.util.Set;

/**
 * 用户业务对象
 *
 * @author kai
 * @date 2021/2/2 10:07
 */
public class AuthUserBo extends BaseBo implements IAuthUser {

    /**
     * 姓名
     */
    @QueryCondition(column = "name")
    private String name;

    /**
     * 账户名
     */
    @QueryCondition(column = "userName")
    private String userName;

    /**
     * 密码
     */
    @QueryCondition(column = "password")
    private String password;

    /**
     * root用户
     */
    @QueryCondition(column = "isRoot")
    private Boolean isRoot;

    /**
     * 维护角色列表关系
     */
    private Set<String> authRoleIds;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isRoot() {
        return isRoot;
    }

    @Override
    public Set<String> getRoleSet() {
        return authRoleIds;
    }

    //getter setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Set<String> getAuthRoleIds() {
        return authRoleIds;
    }

    public void setAuthRoleIds(Set<String> authRoleIds) {
        this.authRoleIds = authRoleIds;
    }
}
