package com.biocv.boot.auth.support;

import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.service.AuthUserService;
import com.biocv.boot.autoconfigure.security.support.IAuthUser;
import com.biocv.boot.autoconfigure.security.support.UserSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kai
 * @date 2021/2/2 11:05
 */
@Component
public class AuthUserSupport implements UserSupport {

    @Autowired
    private AuthUserService authUserService;

    @Override
    public IAuthUser getUserByUserName(String userName) {
        AuthUserBo authUserBo = authUserService.getByUserName(userName);
        return authUserBo;
    }
}
