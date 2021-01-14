package com.biocv.boot.vis.staff;

import com.biocv.boot.autoconfigure.auth.AuthConstants;
import com.biocv.boot.autoconfigure.auth.dao.AuthPermissionRepository;
import com.biocv.boot.autoconfigure.auth.model.AuthPermission;
import com.biocv.boot.vis.staff.service.StaffService;
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
    private AuthPermissionRepository authPermissionRepository;

    @Override
    public void run(String... args) throws Exception {
        initPermission();
    }

    /**
     * 初始权限数据
     */
    private void initPermission() {

        AuthPermission modulePermission = new AuthPermission();
        modulePermission.setName("员工模块");
        modulePermission.setCode("staff:module");
        modulePermission.setType(AuthConstants.AUTH_MODULE);
        authPermissionRepository.save(modulePermission);

        AuthPermission menuPermission = new AuthPermission();
        menuPermission.setName("员工菜单");
        menuPermission.setCode("staff:menu");
        menuPermission.setType(AuthConstants.AUTH_MENU);
        menuPermission.setParent(modulePermission);
        authPermissionRepository.save(menuPermission);

        AuthPermission apiPermission = new AuthPermission();
        apiPermission.setName("员工列表查询");
        apiPermission.setCode("staff:api:list");
        apiPermission.setMethod("post");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParent(menuPermission);
        authPermissionRepository.save(apiPermission);

        apiPermission = new AuthPermission();
        apiPermission.setName("员工新增");
        apiPermission.setCode("staff:api:save");
        apiPermission.setMethod("post");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParent(menuPermission);
        authPermissionRepository.save(apiPermission);

        apiPermission = new AuthPermission();
        apiPermission.setName("员工删除");
        apiPermission.setCode("staff:api:delete");
        apiPermission.setMethod("delete");
        apiPermission.setUri("/staff");
        apiPermission.setType(AuthConstants.AUTH_API);
        apiPermission.setParent(menuPermission);
        authPermissionRepository.save(apiPermission);
    }
}
