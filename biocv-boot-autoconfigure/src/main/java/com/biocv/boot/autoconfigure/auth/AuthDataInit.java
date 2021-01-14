package com.biocv.boot.autoconfigure.auth;

import com.biocv.boot.autoconfigure.auth.dao.AuthUserRepository;
import com.biocv.boot.autoconfigure.auth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * 权限数据初始化
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:37
 * @since 1.0.0
 */
public class AuthDataInit implements CommandLineRunner {

    private AuthUserRepository authUserRepository;


    @Override
    public void run(String... args) throws Exception {
        AuthUser authUser = new AuthUser();
        authUser.setUserName("admin");
        authUser.setPassword("admin");
        authUser.setIsRoot(true);
        authUserRepository.save(authUser);
    }

    @Autowired
    public void setAuthUserRepository(AuthUserRepository authUserRepository){
        this.authUserRepository = authUserRepository;
    }

}
