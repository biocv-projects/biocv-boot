package com.biocv.boot.autoconfigure.auth.controller;

import com.biocv.boot.autoconfigure.auth.AuthPermissionService;
import com.biocv.boot.autoconfigure.auth.dto.AuthRoleDto;
import com.biocv.boot.autoconfigure.auth.dto.AuthRolePermissionDto;
import com.biocv.boot.autoconfigure.auth.dto.AuthUserDto;
import com.biocv.boot.web.BioCVResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制器
 *
 * @author Tyler.Feng
 * @date 2021-01-14 16:24
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
public class AuthPermissionController {

    @Autowired
    private AuthPermissionService authPermissionService;

    @PostMapping("/user")
    public BioCVResult addUser(@RequestBody AuthUserDto authUserDto){
        authPermissionService.saveUser(authUserDto);
        return BioCVResult.success(null);
    }

    @PostMapping("/role")
    public BioCVResult addRole(@RequestBody AuthRoleDto authRoleDto){
        authPermissionService.saveRole(authRoleDto);
        return BioCVResult.success(null);
    }

    @PostMapping("/role/permission")
    public BioCVResult addPermission(@RequestBody AuthRolePermissionDto authRolePermissionDto){
        String roleId = authRolePermissionDto.getRoleId();
        List<String> permissionIds = authRolePermissionDto.getPermissions();
        authPermissionService.addPermission(roleId,permissionIds);
        return BioCVResult.success(null);
    }

}
