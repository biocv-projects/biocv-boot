package com.biocv.boot.vis.staff.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.staff.service.StaffService;
import com.biocv.boot.vis.staff.dao.StaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * staff service实现
 *
 * @author Tyler.Feng
 * @date 2021-01-12 16:51
 * @since 1.0.0
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize) {
//        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
//        Page<Staff> page = staffDao.findByPage(condition, pageIndex, pageSize);
//        Pager pager = new Pager();
//        pager.setPageIndex(pageIndex);
//        pager.setPageSize(page.getTotalPages());
//        pager.setPageSize(page.getSize());
//        List<StaffBo> staffBos = mapperFacade.mapAsList(page.getContent(), StaffBo.class);
//        pager.setData(page.getContent());
//        return pager;
        return null;
    }
}
