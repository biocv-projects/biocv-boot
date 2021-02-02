package com.biocv.boot.auth.service.impl;

import com.biocv.boot.auth.dao.AuthPermissionRepository;
import com.biocv.boot.auth.dao.AuthRoleRepository;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;
import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.auth.service.AuthRoleService;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色服务实现
 *
 * @author kai
 * @date 2021/2/2 10:24
 */
@Service
@Transactional
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private ConfigurableMapper configurableMapper;

    @Override
    public void save(AuthRoleBo authRoleBo) {
        AuthRole authRole = Optional.ofNullable(authRoleBo)
                .map(i->i.getId())
                .filter(StringUtils::isNotBlank)
                .flatMap(authRoleRepository::findById)
                .orElse(new AuthRole());

        configurableMapper.map(authRoleBo,authRole);

        Set<AuthPermission> authPermissionSet = new HashSet<>();
        Set<String> authPermissionIds = authRoleBo.getAuthPermissionIds();
        for (String authPermissionId: authPermissionIds){
            Optional<AuthPermission> optional = authPermissionRepository.findById(authPermissionId);
            if (optional.isPresent()){
                authPermissionSet.add(optional.get());
            }
        }
        authRole.setAuthPermissionSet(authPermissionSet);
    }

    @Override
    public void addPermission(String roleId, List<String> permissionSet) {
        AuthRole authRole = authRoleRepository.getOne(roleId);
        Set<AuthPermission> authPermissionList = new HashSet<>();
        for (String permission : permissionSet){
            AuthPermission authPermission = authPermissionRepository.getOne(permission);
            authPermissionList.add(authPermission);
        }
        authRole.setAuthPermissionSet(authPermissionList);
        authRoleRepository.save(authRole);
    }

    @Override
    public List<AuthPermissionBo> getPermissionSetByCode(String code) {
        AuthRole authRole = authRoleRepository.findByCode(code);
        Set<AuthPermission> authPermissionSet = authRole.getAuthPermissionSet();
        List<AuthPermissionBo> authPermissionBos = configurableMapper.mapAsList(authPermissionSet, AuthPermissionBo.class);
        return authPermissionBos;
    }
}
