package com.biocv.boot.auth.domain.bo;

import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;

/**
 * Auth Permission bo
 *
 * @author kai
 * @date 2021/2/2 10:36
 */
public class AuthPermissionBo extends BaseBo {

    /**
     * 权限名称
     */
    @QueryCondition(column = "name")
    private String name;

    /**
     * 权限编码
     */
    @QueryCondition(column = "code")
    private String code;

    /**
     * 权限类型
     *         MODULE：模块
     *          MENU:菜单
     *              API:api接口权限 (真实的权限)
     *              WIDGET:控件 (parent)
     */
    @QueryCondition(column = "type")
    private String type;

    /**
     * api 请求方法 GET POST PUT DELETE
     */
    @QueryCondition(column = "method")
    private String method;

    /**
     * api 请求路径
     */
    @QueryCondition(column = "uri")
    private String uri;

    /**
     * 所属权限
     */
    private String parentId;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
