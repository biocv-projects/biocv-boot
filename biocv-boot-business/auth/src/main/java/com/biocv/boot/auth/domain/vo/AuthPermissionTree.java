package com.biocv.boot.auth.domain.vo;

import java.util.Set;

/**
 * 权限树
 * @author kai
 * @date 2021/2/3 10:28
 */
public class AuthPermissionTree {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 子
     */
    private Set<AuthPermissionTree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Set<AuthPermissionTree> getChildren() {
        return children;
    }

    public void setChildren(Set<AuthPermissionTree> children) {
        this.children = children;
    }
}
