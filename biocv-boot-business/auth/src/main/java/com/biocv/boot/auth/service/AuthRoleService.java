package com.biocv.boot.auth.service;

import com.biocv.boot.Pager;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;

import java.util.List;

/**
 * Auth Role Service
 *
 * @author kai
 * @date 2021/2/2 10:21
 */
public interface AuthRoleService {

    /**
     * 分页查询
     *
     * @param authRoleBo
     * @return com.biocv.boot.Pager
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 17:47
     * @since 1.0.0
    */
    Pager getByPager(AuthRoleBo authRoleBo);

    /**
     * 保存
     *
     * @param authRoleBo
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:23
     * @since 1.0.0
    */
    void save(AuthRoleBo authRoleBo);

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionSet
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:45
     * @since 1.0.0
    */
    void addPermission(String roleId, List<String> permissionSet);

    /**
     * 获取角色的所有权限
     *
     * @param code
     * @return java.util.List<com.biocv.boot.auth.domain.bo.AuthPermissionBo>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 11:03
     * @since 1.0.0
    */
    List<AuthPermissionBo> getPermissionSetByCode(String code);

    /**
     * get by id
     *
     * @param id
     * @return com.biocv.boot.auth.domain.bo.AuthRoleBo
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 15:29
     * @since 1.0.0
    */
    AuthRoleBo getById(String id);

    /**
     * 条件查询
     *
     * @param authRoleBo
     * @return java.util.List<com.biocv.boot.auth.domain.bo.AuthRoleBo>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 16:44
     * @since 1.0.0
    */
    List<AuthRoleBo> getByCondition(AuthRoleBo authRoleBo);

}
