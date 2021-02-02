package com.biocv.boot.autoconfigure.security.support;

/**
 * @author kai
 * @date 2021/2/2 09:39
 */
public interface UserSupport {

    /**
     * 通过用户名查找
     *
     * @param userName 用户名
     * @return com.biocv.boot.autoconfigure.security.support.IAuthUser
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 09:40
     * @since 1.0.0
    */
    IAuthUser getUserByUserName(String userName);

}
