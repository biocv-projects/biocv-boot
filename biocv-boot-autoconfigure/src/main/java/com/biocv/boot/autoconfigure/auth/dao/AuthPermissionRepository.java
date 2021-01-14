package com.biocv.boot.autoconfigure.auth.dao;


import com.biocv.boot.autoconfigure.auth.model.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * 权限dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
@Transactional
public interface AuthPermissionRepository extends JpaRepository<AuthPermission,String> {
}
