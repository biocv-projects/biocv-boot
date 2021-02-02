package com.biocv.boot.vis.staff.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.staff.bo.StaffBto;
import com.biocv.boot.vis.staff.dao.StaffDao;
import com.biocv.boot.vis.staff.model.Staff;
import com.biocv.boot.vis.staff.service.StaffService;
import com.biocv.protocol.UploadUserEvent;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
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
public class StaffServiceImpl implements StaffService, ApplicationListener<UploadUserEvent> {


    @Autowired
    private StaffDao staffDao;

    @Override
    public Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Staff.class, StaffBto.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Page<Staff> page = staffDao.findByPage(condition, pageIndex, pageSize);
        Pager pager = new Pager();
        pager.setPageIndex(pageIndex);
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<StaffBto> staffBos = mapperFacade.mapAsList(page.getContent(), StaffBto.class);
        pager.setData(staffBos);
        return pager;
    }

    @Override
    public void save(StaffBto staffBo) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(StaffBto.class,Staff.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Staff staff = mapperFacade.map(staffBo, Staff.class);
        staffDao.save(staff);
    }

    @Override
    public void deleteByIds(String ids) {
        staffDao.deleteById(ids);
    }

    /**
     * 自动保存设备上传的人员
     */
    @Override
    public void onApplicationEvent(UploadUserEvent event) {
        StaffBto staffBo = new StaffBto();
        BeanUtils.copyProperties(event,staffBo);
        this.save(staffBo);
    }
}
