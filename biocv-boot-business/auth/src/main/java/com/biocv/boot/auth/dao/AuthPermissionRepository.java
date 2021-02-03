package com.biocv.boot.auth.dao;


import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;
import org.springframework.stereotype.Repository;


/**
 * 权限dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthPermissionRepository extends BaseRepository<AuthPermission,String> {

    /**
     * 根据code查找
     *
     * @param code
     * @return com.biocv.boot.auth.domain.model.AuthPermission
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-03 11:25
     * @since 1.0.0
    */
    AuthPermission findByCode(String code);

}
