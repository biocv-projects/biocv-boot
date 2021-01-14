package com.biocv.boot.autoconfigure.auth;

import com.biocv.boot.autoconfigure.auth.dao.AuthPermissionRepository;
import com.biocv.boot.autoconfigure.auth.dao.AuthRoleRepository;
import com.biocv.boot.autoconfigure.auth.dao.AuthUserRepository;
import com.biocv.boot.autoconfigure.auth.dto.AuthRoleDto;
import com.biocv.boot.autoconfigure.auth.dto.AuthUserDto;
import com.biocv.boot.autoconfigure.auth.model.AuthPermission;
import com.biocv.boot.autoconfigure.auth.model.AuthRole;
import com.biocv.boot.autoconfigure.auth.model.AuthUser;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 权限服务
 *
 * @author Tyler.Feng
 * @date 2021-01-14 11:43
 * @since 1.0.0
 */
public class AuthPermissionService {

    private AuthPermissionRepository authPermissionRepository;

    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private ConfigurableMapper configurableMapper;

    /**
     * 添加用户
     *
     * @param authUserDto
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-14 17:19
     * @since 1.0.0
     */
    public void saveUser(AuthUserDto authUserDto){
        AuthUser authUser = Optional.ofNullable(authUserDto)
                .map(i->i.getId())
                .filter(StringUtils::isNotBlank)
                .flatMap(authUserRepository::findById)
                .orElse(new AuthUser());

        configurableMapper.map(authUserDto,authUser);
        Set<String> authRoleIds = authUserDto.getAuthRoleIds();
        if (!CollectionUtils.isEmpty(authRoleIds)){
            Set<AuthRole> authRoleSet = new HashSet<>();
            for (String authRoleId : authRoleIds){
                AuthRole authRole = authRoleRepository.getOne(authRoleId);
                authRoleSet.add(authRole);
            }
            authUser.setAuthRoleSet(authRoleSet);
        }
        authUserRepository.save(authUser);
    }

    /**
     * 保存角色
     *
     * @param authRoleDto
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-14 11:49
     * @since 1.0.0
     */
    public void saveRole(AuthRoleDto authRoleDto){
        String code = authRoleDto.getCode();
        String name = authRoleDto.getName();
        Set<String> permissionSet = authRoleDto.getPermissionSet();

        Set<AuthPermission> authPermissionSet = new HashSet<>();
        if (permissionSet != null){
            for (String permission : permissionSet){
                AuthPermission authPermission = authPermissionRepository.getOne(permission);
                authPermissionSet.add(authPermission);
            }
        }

        AuthRole authRole = new AuthRole();
        authRole.setCode(code);
        authRole.setName(name);
        authRole.setAuthPermissionSet(authPermissionSet);
        authRoleRepository.save(authRole);
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionSet
     * @return com.biocv.boot.web.BioCVResult
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-14 16:33
     * @since 1.0.0
     */
    public void addPermission(String roleId, List<String> permissionSet){
        AuthRole authRole = authRoleRepository.getOne(roleId);
        Set<AuthPermission> authPermissionList = new HashSet<>();
        for (String permission : permissionSet){
            AuthPermission authPermission = authPermissionRepository.getOne(permission);
            authPermissionList.add(authPermission);
        }
        authRole.setAuthPermissionSet(authPermissionList);
        authRoleRepository.save(authRole);
    }

    @Autowired
    public void setAuthPermissionRepository(AuthPermissionRepository authPermissionRepository){
        this.authPermissionRepository = authPermissionRepository;
    }

    @Autowired
    public void setAuthRoleRepository(AuthRoleRepository authRoleRepository){
        this.authRoleRepository = authRoleRepository;
    }

}
