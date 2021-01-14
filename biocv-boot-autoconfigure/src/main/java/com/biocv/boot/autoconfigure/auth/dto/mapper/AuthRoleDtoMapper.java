package com.biocv.boot.autoconfigure.auth.dto.mapper;

import com.biocv.boot.autoconfigure.auth.dto.AuthRoleDto;
import com.biocv.boot.autoconfigure.auth.model.AuthRole;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * bean复制
 *
 * @author Tyler.Feng
 * @date 2021-01-14 17:24
 * @since 1.0.0
 */
@Component
public class AuthRoleDtoMapper extends CustomMapper<AuthRoleDto, AuthRole> {
}
