package com.biocv.boot.auth.service.impl;

import com.biocv.boot.auth.dao.AuthPermissionRepository;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.auth.service.AuthPermissionService;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限服务
 *
 * @author Tyler.Feng
 * @date 2021-01-14 11:43
 * @since 1.0.0
 */
@Service
@Transactional
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private ConfigurableMapper configurableMapper;

    @Override
    public AuthPermissionBo save(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = Optional.ofNullable(authPermissionBo)
                .map(i->i.getId())
                .filter(StringUtils::isNotBlank)
                .flatMap(authPermissionRepository::findById)
                .orElse(new AuthPermission());

        configurableMapper.map(authPermissionBo, authPermission);

        String parentId = authPermissionBo.getParentId();
        if (!StringUtils.isEmpty(parentId)){
            AuthPermission parent = authPermissionRepository.findById(parentId).orElse(null);
            authPermission.setParent(parent);
        }
        AuthPermission result = authPermissionRepository.save(authPermission);
        authPermissionBo.setId(result.getId());
        return authPermissionBo;
    }

    @Override
    public AuthPermissionBo getById(String id) {
        Optional<AuthPermission> optional = authPermissionRepository.findById(id);
        if (optional.isPresent()){
            AuthPermission authPermission = optional.get();
            AuthPermissionBo bo = configurableMapper.map(authPermission, AuthPermissionBo.class);
            return bo;
        }
        return null;

    }

    @Override
    public Set<String> getAllPermission() {
        List<AuthPermission> all = authPermissionRepository.findAll();
        List<AuthPermissionBo> authPermissionBos = configurableMapper.mapAsList(all, AuthPermissionBo.class);
        Set<String> collect = authPermissionBos.stream().map(AuthPermissionBo::getCode).collect(Collectors.toSet());
        return collect;
    }
}
