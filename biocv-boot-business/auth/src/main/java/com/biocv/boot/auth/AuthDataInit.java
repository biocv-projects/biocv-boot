package com.biocv.boot.auth;

import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.service.AuthPermissionService;
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

    @Autowired
    private AuthPermissionService authPermissionService;

    @Override
    public void run(String... args) throws Exception {
        AuthUserBo authUserBo = new AuthUserBo();
        authUserBo.setUserName("admin");
        authUserBo.setPassword("admin");
        authUserBo.setIsRoot(true);
        authUserService.save(authUserBo);

        initPermission();

    }

    /**
     * 初始权限
     *
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-03 17:45
     * @since 1.0.0
    */
    private void initPermission() {
        //模块
        AuthPermissionBo authPermissionBo = new AuthPermissionBo();
        authPermissionBo.setType(AuthConstants.AUTH_MODULE);
        authPermissionBo.setName("授权管理");
        authPermissionBo.setCode("auth");
        AuthPermissionBo module = authPermissionService.save(authPermissionBo);

        //菜单
        AuthPermissionBo menuPermissionBo = new AuthPermissionBo();
        menuPermissionBo.setType(AuthConstants.AUTH_MENU);
        menuPermissionBo.setName("用户");
        menuPermissionBo.setCode("auth:user");
        menuPermissionBo.setParentId(module.getId());
        AuthPermissionBo menu = authPermissionService.save(menuPermissionBo);

        //接口
        AuthPermissionBo apiPermissionBo = new AuthPermissionBo();
        apiPermissionBo.setType(AuthConstants.AUTH_API);
        apiPermissionBo.setName("保存用户");
        apiPermissionBo.setCode("auth:user:save");
        apiPermissionBo.setParentId(menu.getId());
        authPermissionService.save(apiPermissionBo);

        apiPermissionBo = new AuthPermissionBo();
        apiPermissionBo.setType(AuthConstants.AUTH_API);
        apiPermissionBo.setName("查看用户列表");
        apiPermissionBo.setCode("auth:user:list");
        apiPermissionBo.setParentId(menu.getId());
        authPermissionService.save(apiPermissionBo);

        apiPermissionBo = new AuthPermissionBo();
        apiPermissionBo.setType(AuthConstants.AUTH_API);
        apiPermissionBo.setName("保存角色");
        apiPermissionBo.setCode("auth:role:save");
        apiPermissionBo.setParentId(menu.getId());
        authPermissionService.save(apiPermissionBo);

        apiPermissionBo = new AuthPermissionBo();
        apiPermissionBo.setType(AuthConstants.AUTH_API);
        apiPermissionBo.setName("查看角色列表");
        apiPermissionBo.setCode("auth:role:list");
        apiPermissionBo.setParentId(menu.getId());
        authPermissionService.save(apiPermissionBo);

        apiPermissionBo = new AuthPermissionBo();
        apiPermissionBo.setType(AuthConstants.AUTH_API);
        apiPermissionBo.setName("查看权限列表");
        apiPermissionBo.setCode("auth:permission:list");
        apiPermissionBo.setParentId(menu.getId());
        authPermissionService.save(apiPermissionBo);

    }

}
