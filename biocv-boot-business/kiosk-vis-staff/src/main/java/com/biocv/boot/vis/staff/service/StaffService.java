package com.biocv.boot.vis.staff.service;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;

/**
 * 员工操作接口
 *
 * @author Tyler.Feng
 * @date 2021-01-12 16:50
 * @since 1.0.0
 */
public interface StaffService {

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

}
