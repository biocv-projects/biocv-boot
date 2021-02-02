package com.biocv.boot.auth.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.auth.dao.AuthRoleRepository;
import com.biocv.boot.auth.dao.AuthUserRepository;
import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.domain.model.AuthRole;
import com.biocv.boot.auth.domain.model.AuthUser;
import com.biocv.boot.auth.service.AuthUserService;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author kai
 * @date 2021/2/1 17:11
 */
@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private ConfigurableMapper configurableMapper;

    @Override
    public Pager getAuthUserListByPager(AuthUserBo authUserBo) {
        Page<AuthUser> page = authUserRepository.findByPage(authUserBo, authUserBo.getPageIndex(), authUserBo.getPageSize());
        Pager pager = new Pager();
        pager.setPageIndex(authUserBo.getPageIndex());
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<AuthUserBo> authUserBos = configurableMapper.mapAsList(page.getContent(), AuthUserBo.class);
        pager.setData(authUserBos);
        return pager;
    }

    @Override
    public void save(AuthUserBo authUserBo) {
        AuthUser authUser = Optional.ofNullable(authUserBo)
                .map(i->i.getId())
                .filter(StringUtils::isNotBlank)
                .flatMap(authUserRepository::findById)
                .orElse(new AuthUser());

        authUser.setIsRoot(authUserBo.getRoot());

        configurableMapper.map(authUserBo,authUser);
        Set<String> authRoleIds = authUserBo.getAuthRoleIds();
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

    @Override
    public AuthUserBo getByUserName(String userName) {
        AuthUser authUser = authUserRepository.findByUserName(userName);
        AuthUserBo authUserBo = configurableMapper.map(authUser, AuthUserBo.class);
        authUserBo.setRoot(authUser.getIsRoot());
        return authUserBo;
    }
}
