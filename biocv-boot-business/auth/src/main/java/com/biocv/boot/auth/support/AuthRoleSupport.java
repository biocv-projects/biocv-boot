package com.biocv.boot.auth.support;

import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.service.AuthRoleService;
import com.biocv.boot.autoconfigure.security.support.RoleSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kai
 * @date 2021/2/2 11:00
 */
@Component
public class AuthRoleSupport implements RoleSupport {

    @Autowired
    private AuthRoleService authRoleService;

    @Override
    public Set<String> getAuthorizationSet(String roleCode) {
        List<AuthPermissionBo> authPermissionBoList = authRoleService.getPermissionSetByCode(roleCode);
        Set<String> collect = authPermissionBoList.stream().map(AuthPermissionBo::getCode).collect(Collectors.toSet());
        return collect;
    }
}
