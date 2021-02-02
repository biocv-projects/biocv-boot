package com.biocv.boot.autoconfigure.security.support;

import java.util.Set;

/**
 * Auth User
 *
 * @author kai
 * @date 2021/2/2 09:36
 */
public interface IAuthUser {

    /**
     * 获取用户名
     *
     * @return java.lang.String
     * @date  2021-02-02 09:38
     * @since 1.0.0
    */
    String getUserName();

    /**
     * 获取密码
     *
     * @return java.lang.String
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:38
     * @since 1.0.0
    */
    String getPassword();

    /**
     * 是否超级用户
     *
     * @return boolean
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:38
     * @since 1.0.0
    */
    boolean isRoot();

    /**
     * 角色列表
     *
     * @return java.util.Set<java.lang.String>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:39
     * @since 1.0.0
    */
    Set<String> getRoleSet();

}
