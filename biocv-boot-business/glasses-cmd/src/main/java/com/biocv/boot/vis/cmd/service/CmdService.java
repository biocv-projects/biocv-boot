package com.biocv.boot.vis.cmd.service;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.cmd.bo.CmdBo;

/**
 * cmd 服务接口
 *
 * @author Tyler.Feng
 * @date 2021-01-29 14:43
 * @since 1.0.0
 */
public interface CmdService {

    /**
     * 分页查询
     *
     * @param condition
     * @param pageIndex
     * @param pageSize
     * @return com.biocv.boot.Pager
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-12 16:59
     * @since 1.0.0
     */
    Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize);

    /**
     * 保存
     *
     * @param cmdBo
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-13 15:55
     * @since 1.0.0
     */
    void save(CmdBo cmdBo);

    /**
     * 根据id删除
     *
     * @param ids
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-13 16:03
     * @since 1.0.0
     */
    void deleteByIds(String ids);

    /**
     * 清空命令
     *
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2021-02-01 15:28
     * @since 1.0.0
    */
    void clear();
}
