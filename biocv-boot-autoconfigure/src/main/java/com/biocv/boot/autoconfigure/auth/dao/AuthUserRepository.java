package com.biocv.boot.autoconfigure.auth.dao;


import com.biocv.boot.autoconfigure.auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * 用户dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
@Transactional
public interface AuthUserRepository extends JpaRepository<AuthUser,String> {

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
