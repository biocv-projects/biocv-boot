package com.biocv.boot.auth;

import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 权限数据初始化
 *
 * @author Tyler.Feng
 * @date 2021-01-12 11:37
 * @since 1.0.0
 */
@Component
public class AuthDataInit implements CommandLineRunner {

    @Autowired
    private AuthUserService authUserService;

    @Override
    public void run(String... args) throws Exception {
        AuthUserBo authUserBo = new AuthUserBo();
        authUserBo.setUserName("admin");
        authUserBo.setPassword("admin");
        authUserBo.setRoot(true);
        authUserService.save(authUserBo);
    }

}
