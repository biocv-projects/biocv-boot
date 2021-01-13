package com.biocv.boot.autoconfigure.auth.dao;

import com.biocv.boot.autoconfigure.auth.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole,String> {
}
