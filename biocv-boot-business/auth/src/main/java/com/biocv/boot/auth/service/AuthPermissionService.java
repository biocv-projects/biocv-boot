package com.biocv.boot.auth.service;

import com.biocv.boot.auth.domain.bo.AuthPermissionBo;

import java.util.Set;

/**
 * Auth Permission Service
 *
 * @author kai
 * @date 2021/2/2 10:10
 */
public interface AuthPermissionService {

    /**
     * 保存
     *
     * @param authPermissionBo
     * @return com.biocv.boot.auth.domain.bo.AuthPermissionBo
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 11:16
     * @since 1.0.0
    */
    AuthPermissionBo save(AuthPermissionBo authPermissionBo);

    /**
     * Get by id
     *
     * @param id
     * @return com.biocv.boot.auth.domain.bo.AuthPermissionBo
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:38
     * @since 1.0.0
    */
    AuthPermissionBo getById(String id);

    /**
     * 获取所有权限集合
     *
     * @return java.util.Set<java.lang.String>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:57
     * @since 1.0.0
    */
    Set<String> getAllPermission();
}
