package com.biocv.boot.vis.device.service;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.device.bo.GlassesDeviceBto;

/**
 * 眼镜设备接口
 *
 * @author Tyler.Feng
 * @date 2021-01-29 15:38
 * @since 1.0.0
 */
public interface GlassesDeviceService {

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
    void save(GlassesDeviceBto cmdBo);

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

}
