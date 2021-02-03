package com.biocv.boot.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.biocv.boot.Pager;
import com.biocv.boot.auth.dao.AuthPermissionRepository;
import com.biocv.boot.auth.dao.AuthRoleRepository;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;
import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.auth.domain.model.AuthUser;
import com.biocv.boot.auth.service.AuthRoleService;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Pager getByPager(AuthRoleBo authRoleBo) {
        Page<AuthRole> page = authRoleRepository.findByPage(authRoleBo, authRoleBo.getPageIndex(), authRoleBo.getPageSize());
        Pager pager = new Pager();
        pager.setPageIndex(authRoleBo.getPageIndex());
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<AuthRoleBo> authRoleBos = configurableMapper.mapAsList(page.getContent(), AuthRoleBo.class);
        pager.setData(authRoleBos);
        return pager;
    }

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
        if (!CollectionUtil.isEmpty(authPermissionIds)){
            for (String authPermissionId: authPermissionIds){
                Optional<AuthPermission> optional = authPermissionRepository.findById(authPermissionId);
                if (optional.isPresent()){
                    authPermissionSet.add(optional.get());
                }
            }
        }

        authRole.setAuthPermissionSet(authPermissionSet);
        authRoleRepository.save(authRole);
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

    @Override
    public AuthRoleBo getById(String id) {
        Optional<AuthRole> optional = authRoleRepository.findById(id);
        if (optional.isPresent()){
            AuthRole authRole = optional.get();
            AuthRoleBo bo = configurableMapper.map(authRole, AuthRoleBo.class);
            return bo;
        }
        return null;
    }

    @Override
    public List<AuthRoleBo> getByCondition(AuthRoleBo authRoleBo) {
        List<AuthRole> authRoleList = authRoleRepository.findByCondition(authRoleBo);
        List<AuthRoleBo> authRoleBos = configurableMapper.mapAsList(authRoleList, AuthRoleBo.class);
        return authRoleBos;
    }
}
