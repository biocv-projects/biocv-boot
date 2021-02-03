package com.biocv.boot.auth.service;

import com.biocv.boot.Pager;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;

import java.util.List;
import java.util.Set;

/**
 * Auth Permission Service
 *
 * @author kai
 * @date 2021/2/2 10:10
 */
public interface AuthPermissionService {

    /**
     * 分页查询
     *
     * @param authPermissionBo
     * @return com.biocv.boot.Pager
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 17:47
     * @since 1.0.0
     */
    Pager getByPager(AuthPermissionBo authPermissionBo);

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

    /**
     * 查看某个权限编码以及所有子权限
     *
     * @param code
     * @return java.util.Set<com.biocv.boot.auth.domain.bo.AuthPermissionBo>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-03 11:07
     * @since 1.0.0
    */
    AuthPermissionBo getPermissionByCode(String code);

    /**
     * 根据code查询
     *
     * @param code
     * @return com.biocv.boot.auth.domain.bo.AuthPermissionBo
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 19:45
     * @since 1.0.0
    */
    AuthPermissionBo getByCode(String code);

    /**
     * 条件查询
     *
     * @param authPermissionBo
     * @return java.util.List<com.biocv.boot.auth.domain.bo.AuthPermissionBo>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 19:50
     * @since 1.0.0
    */
    List<AuthPermissionBo> getByCondition(AuthPermissionBo authPermissionBo);
}
