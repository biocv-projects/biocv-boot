package com.biocv.boot.autoconfigure.auth.dao;

import com.biocv.boot.autoconfigure.auth.model.AuthUser;
import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthUserRepository extends BaseRepository<AuthUser,String> {
}
