package com.biocv.boot.autoconfigure.security.support;

import java.util.Set;

/**
 * 权限支持接口
 * @author kai
 * @date 2021/2/2 09:28
 */
public interface AuthorizationSupport {

    /**
     * 获取所有权限集合
     *
     * @return java.util.Set<java.lang.String>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:29
     * @since 1.0.0
    */
    Set<String> getAllAuthorization();

}
