package com.biocv.boot.auth.support;

import com.biocv.boot.autoconfigure.security.support.IAuthUser;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author kai
 * @date 2021/2/2 11:09
 */
public class AuthUserBean implements IAuthUser {


    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public Set<String> getRoleSet() {
        return null;
    }
}
