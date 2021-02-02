package com.biocv.boot.auth.domain.dto;

import com.biocv.boot.pojo.BaseDto;
import lombok.Data;

import java.util.Set;

/**
 * 用户数据传输对象
 *
 * @author Tyler.Feng
 * @date 2021-01-14 17:15
 * @since 1.0.0
 */
@Data
public class AuthUserDto extends BaseDto {

    /**
     * ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * root用户
     */
    private Boolean isRoot;

    /**
     * 角色列表
     */
    private Set<String> authRoleIds;


}
