package com.biocv.boot.auth.dao;


import com.biocv.boot.auth.domain.model.AuthUser;
import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;

/**
 * 用户dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthUserRepository extends BaseRepository<AuthUser,String> {

    /**
     * 根据账号查找
     *
     * @param userName
     * @return com.biocv.auth.model.AuthUser
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-13 19:43
     * @since 1.0.0
     */
    AuthUser findByUserName(String userName);
}
