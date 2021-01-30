package com.biocv.boot.vis.staff.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.staff.bo.StaffBo;
import com.biocv.boot.vis.staff.dao.StaffDao;
import com.biocv.boot.vis.staff.model.Staff;
import com.biocv.boot.vis.staff.service.StaffService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * staff service实现
 *
 * @author Tyler.Feng
 * @date 2021-01-12 16:51
 * @since 1.0.0
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Autowired
    private StaffDao staffDao;

    @Override
    public Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Staff.class,StaffBo.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Page<Staff> page = staffDao.findByPage(condition, pageIndex, pageSize);
        Pager pager = new Pager();
        pager.setPageIndex(pageIndex);
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<StaffBo> staffBos = mapperFacade.mapAsList(page.getContent(), StaffBo.class);
        pager.setData(staffBos);
        return pager;
    }

    @Override
    public void save(StaffBo staffBo) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(StaffBo.class,Staff.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Staff staff = mapperFacade.map(staffBo, Staff.class);
        staffDao.save(staff);
    }

    @Override
    public void deleteByIds(String ids) {
        staffDao.deleteById(ids);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


}
