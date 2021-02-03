package com.biocv.boot.auth.domain.dto;

import com.biocv.boot.pojo.BaseDto;

/**
 * @author kai
 * @date 2021/2/2 18:16
 */
public class AuthPermissionDto extends BaseDto {
    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限类型
     *         MODULE：模块
     *          MENU:菜单
     *              API:api接口权限 (真实的权限)
     *              WIDGET:控件 (parent)
     */
    private String type;

    /**
     * api 请求方法 GET POST PUT DELETE
     */
    private String method;

    /**
     * api 请求路径
     */
    private String uri;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
