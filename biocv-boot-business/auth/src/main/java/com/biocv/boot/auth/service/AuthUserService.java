package com.biocv.boot.auth.service;

import com.biocv.boot.Pager;
import com.biocv.boot.auth.domain.bo.AuthUserBo;

/**
 * 用户服务
 *
 * @author kai
 * @date 2021/2/1 19:17
 */
public interface AuthUserService {

    /**
     * 获取用户分页数据
     *
     * @param authUserBo
     * @return com.biocv.boot.Pager
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:07
     * @since 1.0.0
    */
    Pager getAuthUserListByPager(AuthUserBo authUserBo);

    /**
     * 保存用户
     *
     * @param authUserBo
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 10:18
     * @since 1.0.0
    */
    void save(AuthUserBo authUserBo);

    /**
     * get by username
     *
     * @param userName
     * @return com.biocv.boot.auth.domain.bo.AuthUserBo
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-02 12:03
     * @since 1.0.0
    */
    AuthUserBo getByUserName(String userName);

}
