package com.biocv.boot.auth.dao;


import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;

/**
 * 角色dao
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:21
 * @since 1.0.0
 */
public interface AuthRoleRepository extends BaseRepository<AuthRole,String> {

    /**
     * 通过编码查询
     * @param code
     * @return
     */
    AuthRole findByCode(String code);

}
