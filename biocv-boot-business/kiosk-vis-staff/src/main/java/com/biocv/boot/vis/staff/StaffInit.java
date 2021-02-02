package com.biocv.boot.vis.staff;

import com.biocv.boot.auth.AuthConstants;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.service.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 员工模块初始数据
 *
 * @author Tyler.Feng
 * @date 2021-01-14 9:49
 * @since 1.0.0
 */
@Component
public class StaffInit implements CommandLineRunner {

    @Autowired
    private AuthPermissionService authPermissionService;

    @Override
    public void run(String... args) throws Exception {
        initPermission();
    }

    /**
     * 初始权限数据
     */
    private void initPermission() {

        AuthPermissionBo modulePermission = new AuthPermissionBo();
        modulePermission.setName("员工模块");
        modulePermission.setCode("staff:module");
        modulePermission.setType(AuthConstants.AUTH_MODULE);
        modulePermission = authPermissionService.save(modulePermission);

        AuthPermissionBo menuPermission = new AuthPermissionBo();
        menuPermission.setName("员工菜单");
        menuPermission.setCode("staff:menu");
        menuPermission.setType(AuthConstants.AUTH_MENU);
        menuPermission.setParentId(modulePermission.getId());
        menuPermission = authPermissionService.save(menuPermission);

        AuthPermissionBo apiPermission = new AuthPermissionBo();
        apiPermission.setName("员工列表查询");
        apiPermission.setCode("staff:api:list");
        apiPermission.setMethod("post");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParentId(menuPermission.getId());
        authPermissionService.save(apiPermission);

        apiPermission = new AuthPermissionBo();
        apiPermission.setName("员工新增");
        apiPermission.setCode("staff:api:save");
        apiPermission.setMethod("post");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParentId(menuPermission.getId());
        authPermissionService.save(apiPermission);

        apiPermission = new AuthPermissionBo();
        apiPermission.setName("员工删除");
        apiPermission.setCode("staff:api:delete");
        apiPermission.setMethod("delete");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParentId(menuPermission.getId());
        authPermissionService.save(apiPermission);
    }
}
