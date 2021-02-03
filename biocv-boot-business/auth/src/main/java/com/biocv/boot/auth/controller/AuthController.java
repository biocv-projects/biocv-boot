package com.biocv.boot.auth.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.biocv.boot.Pager;
import com.biocv.boot.auth.AuthConstants;
import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
import com.biocv.boot.auth.domain.bo.AuthRoleBo;
import com.biocv.boot.auth.domain.bo.AuthUserBo;
import com.biocv.boot.auth.domain.dto.AuthPermissionDto;
import com.biocv.boot.auth.domain.dto.AuthRoleDto;
import com.biocv.boot.auth.domain.dto.AuthRolePermissionDto;
import com.biocv.boot.auth.domain.dto.AuthUserDto;
import com.biocv.boot.auth.domain.vo.AuthPermissionTree;
import com.biocv.boot.auth.domain.vo.AuthUserVo;
import com.biocv.boot.auth.service.AuthPermissionService;
import com.biocv.boot.auth.service.AuthRoleService;
import com.biocv.boot.auth.service.AuthUserService;
import com.biocv.boot.autoconfigure.security.userDetails.UserInfo;
import com.biocv.boot.web.BioCVResult;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private AuthPermissionService authPermissionService;

    @Autowired
    private ConfigurableMapper configurableMapper;

    @PreAuthorize("hasAuthority('auth:user:save')")
    @PostMapping("/user/save")
    public BioCVResult addUser(@RequestBody AuthUserDto authUserDto){
        AuthUserBo authUserBo = configurableMapper.map(authUserDto, AuthUserBo.class);
        authUserService.save(authUserBo);
        return BioCVResult.success();
    }

    @PreAuthorize("hasAuthority('auth:user:list')")
    @PostMapping("/user/list")
    @ResponseBody
    public BioCVResult getUserList(@RequestBody AuthUserDto authUserDto){
        AuthUserBo authUserBo = configurableMapper.map(authUserDto, AuthUserBo.class);
        authUserBo.setPageIndex(authUserBo.getPageIndex() - 1);
        Pager pager = authUserService.getAuthUserListByPager(authUserBo);
        List<AuthUserBo> boList = (List<AuthUserBo>) pager.getData();
        List<AuthUserVo> voList = new ArrayList<>();
        for (AuthUserBo item: boList){
            AuthUserVo authUserVo = configurableMapper.map(item, AuthUserVo.class);
            authUserVo.setRoot(item.getIsRoot());
            Set<String> authRoleIds = item.getAuthRoleIds();
            String roleName = "";
            if (!CollectionUtil.isEmpty(authRoleIds)){
                for (String roleId : authRoleIds){
                    AuthRoleBo authRoleBo = authRoleService.getById(roleId);
                    roleName += authRoleBo.getName();
                }
                authUserVo.setRoleNames(roleName);
            }
            voList.add(authUserVo);
        }
        pager.setData(voList);
        return BioCVResult.success(pager);
    }

    /**
     * 保存角色
     *
     * @param authRoleDto
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 17:50
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:role:save')")
    @PostMapping("/role/save")
    public BioCVResult addRole(@RequestBody AuthRoleDto authRoleDto){
        AuthRoleBo authRoleBo = configurableMapper.map(authRoleDto, AuthRoleBo.class);
        authRoleService.save(authRoleBo);
        return BioCVResult.success();
    }

    /**
     * 角色列表
     *
     * @param authRoleDto
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 16:54
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:role:list')")
    @PostMapping("/role/list")
    public BioCVResult getRoleList(@RequestBody(required = false) AuthRoleDto authRoleDto){
        authRoleDto = Optional.ofNullable(authRoleDto).orElse(new AuthRoleDto());
        AuthRoleBo authRoleBo = configurableMapper.map(authRoleDto, AuthRoleBo.class);
        List<AuthRoleBo> authRoleBoList = authRoleService.getByCondition(authRoleBo);
        return BioCVResult.success(authRoleBoList);
    }

    /**
     * 角色分页数据
     *
     * @param authRoleDto
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 17:46
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:role:list')")
    @PostMapping("/role/page")
    public BioCVResult getRolePage(@RequestBody(required = false) AuthRoleDto authRoleDto){
        authRoleDto = Optional.ofNullable(authRoleDto).orElse(new AuthRoleDto());
        AuthRoleBo authRoleBo = configurableMapper.map(authRoleDto, AuthRoleBo.class);
        Pager byPager = authRoleService.getByPager(authRoleBo);
        return BioCVResult.success(byPager);
    }

    /**
     * 分配权限
     *
     * @param authRolePermissionDto
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-03 14:38
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:role:save')")
    @PostMapping("/role/permission")
    public BioCVResult addPermission(@RequestBody(required = false) AuthRolePermissionDto authRolePermissionDto){
        String roleId = authRolePermissionDto.getRoleId();
        List<String> permissionIds = authRolePermissionDto.getPermissions();
        authRoleService.addPermission(roleId,permissionIds);
        return BioCVResult.success();
    }

    /**
     * 权限分页数据
     *
     * @param authPermissionDto
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 18:22
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:permission:list')")
    @PostMapping("/permission/page")
    public BioCVResult getPermissionPage(@RequestBody(required = false) AuthPermissionDto authPermissionDto){
        authPermissionDto = Optional.ofNullable(authPermissionDto).orElse(new AuthPermissionDto());
        AuthPermissionBo authPermissionBo = configurableMapper.map(authPermissionDto, AuthPermissionBo.class);
        Pager pager = authPermissionService.getByPager(authPermissionBo);
        return BioCVResult.success(pager);
    }

    /**
     *  获取权限树
     *
     * @return com.biocv.boot.web.BioCVResult
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-03 11:33
     * @since 1.0.0
    */
    @PreAuthorize("hasAuthority('auth:permission:list')")
    @PostMapping("/permission/tree")
    public BioCVResult permissionTree(){
        AuthPermissionBo authPermissionBo = authPermissionService.getPermissionByCode("staff:module");
        AuthPermissionTree tree = getAuthPermissionTree(authPermissionBo);
        return BioCVResult.success(tree.getChildren());
    }

    /**
     * 查询权限树
     * @param authPermissionBo
     * @return
     */
    private AuthPermissionTree getAuthPermissionTree(AuthPermissionBo authPermissionBo) {
        AuthPermissionTree tree = new AuthPermissionTree();
        tree.setId(authPermissionBo.getId());
        tree.setName(authPermissionBo.getName());
        tree.setPid(authPermissionBo.getParentId());
        tree.setPath(authPermissionBo.getUri());
        Set<AuthPermissionBo> children = authPermissionBo.getChildren();

        Set treeChildren = new HashSet();
        for (AuthPermissionBo child: children){
            AuthPermissionTree childTree = getAuthPermissionTree(child);
            treeChildren.add(childTree);
        }
        tree.setChildren(treeChildren);
        return tree;
    }

    @GetMapping("/getAuthedModules")
    public BioCVResult getAuthedModules(){
        List<AuthPermissionBo> authPermissionBoList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<UserInfo.Authorization> authorities = (Collection<UserInfo.Authorization>) authentication.getAuthorities();

        AuthPermissionBo condition = new AuthPermissionBo();
        condition.setType(AuthConstants.AUTH_MODULE);
        List<AuthPermissionBo> moduleList = authPermissionService.getByCondition(condition);

        for (AuthPermissionBo module : moduleList){
            for (UserInfo.Authorization authorization : authorities){
                String authority = authorization.getAuthority();
                if (authority.equals(module.getCode())){
                    authPermissionBoList.add(module);
                }
            }
        }
        return BioCVResult.success(authPermissionBoList);
    }
}
