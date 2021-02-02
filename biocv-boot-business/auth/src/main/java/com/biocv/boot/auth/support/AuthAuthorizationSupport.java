package com.biocv.boot.auth.support;

import com.biocv.boot.auth.service.AuthPermissionService;
import com.biocv.boot.autoconfigure.security.support.AuthorizationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author kai
 * @date 2021/2/2 10:56
 */
@Component
public class AuthAuthorizationSupport implements AuthorizationSupport {

    @Autowired
    private AuthPermissionService authPermissionService;

    @Override
    public Set<String> getAllAuthorization() {
        return authPermissionService.getAllPermission();
    }
}
