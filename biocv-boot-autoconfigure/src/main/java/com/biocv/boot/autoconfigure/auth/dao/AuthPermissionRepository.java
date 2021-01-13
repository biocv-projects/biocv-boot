package com.biocv.boot.autoconfigure.auth.dao;

import com.biocv.boot.autoconfigure.auth.model.AuthPermission;
import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthPermissionRepository extends BaseRepository<AuthPermission,String> {
}
