package com.biocv.boot.auth.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;
import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.domain.dto.AuthRoleDto;
import com.biocv.boot.auth.domain.dto.AuthRolePermissionDto;
import com.biocv.boot.auth.domain.dto.AuthUserDto;
import com.biocv.boot.auth.service.AuthRoleService;
import com.biocv.boot.auth.service.AuthUserService;
import com.biocv.boot.web.BioCVResult;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class AuthController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private ConfigurableMapper configurableMapper;

    @PostMapping("/user")
    public BioCVResult addUser(@RequestBody AuthUserDto authUserDto){
        AuthUserBo authUserBo = configurableMapper.map(authUserDto, AuthUserBo.class);
        authUserService.save(authUserBo);
        return BioCVResult.success();
    }

    @PostMapping("/user/list")
    @ResponseBody
    public BioCVResult getUserList(@RequestBody AuthUserDto authUserDto){
        AuthUserBo authUserBo = configurableMapper.map(authUserDto, AuthUserBo.class);
        Pager pager = authUserService.getAuthUserListByPager(authUserBo);
        return BioCVResult.success(pager);
    }

    @PostMapping("/role")
    public BioCVResult addRole(@RequestBody AuthRoleDto authRoleDto){
        AuthRoleBo authRoleBo = configurableMapper.map(authRoleDto, AuthRoleBo.class);
        authRoleService.save(authRoleBo);
        return BioCVResult.success();
    }

    @PostMapping("/role/permission")
    public BioCVResult addPermission(@RequestBody AuthRolePermissionDto authRolePermissionDto){
        String roleId = authRolePermissionDto.getRoleId();
        List<String> permissionIds = authRolePermissionDto.getPermissions();
        authRoleService.addPermission(roleId,permissionIds);
        return BioCVResult.success();
    }

}
