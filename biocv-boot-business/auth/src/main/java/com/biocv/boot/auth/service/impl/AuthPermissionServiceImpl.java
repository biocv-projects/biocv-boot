package com.biocv.boot.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.biocv.boot.Pager;
import com.biocv.boot.auth.dao.AuthPermissionRepository;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;
import com.biocv.boot.auth.domain.model.AuthPermission;
import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.auth.service.AuthPermissionService;
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
    public Pager getByPager(AuthPermissionBo authPermissionBo) {
        Page<AuthPermission> page = authPermissionRepository.findByPage(authPermissionBo, authPermissionBo.getPageIndex(), authPermissionBo.getPageSize());
        Pager pager = new Pager();
        pager.setPageIndex(authPermissionBo.getPageIndex());
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<AuthPermissionBo> authPermissionBoList = configurableMapper.mapAsList(page.getContent(), AuthPermissionBo.class);
        pager.setData(authPermissionBoList);
        return pager;
    }

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

    @Override
    public AuthPermissionBo getPermissionByCode(String code) {
        AuthPermissionBo condition = new AuthPermissionBo();
        condition.setName(code);
        AuthPermission authPermission = authPermissionRepository.findByCode(code);
        AuthPermissionBo map = configurableMapper.map(authPermission, AuthPermissionBo.class);
        if (authPermission.getParent() != null){
            map.setParentId(authPermission.getParent().getId());
        }
        //处理子节点
        Set<AuthPermission> children = authPermission.getChildren();
        Set<AuthPermissionBo> authPermissionBoSet = new HashSet<>();
        if (!CollectionUtil.isEmpty(children)){
            for (AuthPermission child:children){
                AuthPermissionBo childBo = configurableMapper.map(child, AuthPermissionBo.class);
                if (child.getParent() != null){
                    childBo.setParentId(child.getParent().getId());
                }
                //递归查询
                AuthPermissionBo permissionByCode = getPermissionByCode(child.getCode());
                authPermissionBoSet.add(permissionByCode);
            }
        }
        map.setChildren(authPermissionBoSet);
        return map;
    }

    @Override
    public AuthPermissionBo getByCode(String code) {
        AuthPermissionBo condition = new AuthPermissionBo();
        condition.setCode(code);
        List<AuthPermissionBo> authPermissionBoList = getByCondition(condition);
        if (!CollectionUtil.isEmpty(authPermissionBoList)){
            return authPermissionBoList.get(0);
        }
        return null;
    }

    @Override
    public List<AuthPermissionBo> getByCondition(AuthPermissionBo authPermissionBo) {
        List<AuthPermission> authPermissionList = authPermissionRepository.findByCondition(authPermissionBo);
        List<AuthPermissionBo> authPermissionBoList = configurableMapper.mapAsList(authPermissionList, AuthPermissionBo.class);
        return authPermissionBoList;
    }
}
