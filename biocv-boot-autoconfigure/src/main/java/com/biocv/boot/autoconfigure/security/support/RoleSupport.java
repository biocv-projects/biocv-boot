package com.biocv.boot.autoconfigure.security.support;

import java.util.Set;

/**
 * AuthRole Support
 * @author kai
 * @date 2021/2/2 09:13
 */
public interface RoleSupport {

    /**
     * 获取该角色的权限集合
     *
     * @param roleCode 角色code
     * @return java.util.Set<java.lang.String>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:23
     * @since 1.0.0
    */
    Set<String> getAuthorizationSet(String roleCode);

}
