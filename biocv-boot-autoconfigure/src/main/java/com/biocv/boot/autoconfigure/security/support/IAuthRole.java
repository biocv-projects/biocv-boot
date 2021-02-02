package com.biocv.boot.autoconfigure.security.support;

import java.util.Set;

/**
 * 角色
 *
 * @author kai
 * @date 2021/2/2 09:18
 */
public interface IAuthRole {

    /**
     *  获取权限字符串
     *
     * @return java.util.Set<java.lang.String>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:19
     * @since 1.0.0
    */
    Set<String> getAuthorizationSet();

}
