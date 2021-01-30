package com.biocv.boot.vis.device.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.device.bo.GlassesDeviceBo;
import com.biocv.boot.vis.device.dao.GlassesDeviceDao;
import com.biocv.boot.vis.device.model.GlassesDevice;
import com.biocv.boot.vis.device.service.GlassesDeviceService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 眼镜设备服务实现
 *
 * @author Tyler.Feng
 * @date 2021-01-29 15:39
 * @since 1.0.0
 */
@Service
@Transactional
public class GlassesDeviceServiceImpl implements GlassesDeviceService {

    @Autowired
    private GlassesDeviceDao glassesDeviceDao;

    @Override
    public Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(GlassesDevice.class,GlassesDeviceBo.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Page<GlassesDevice> page = glassesDeviceDao.findByPage(condition, pageIndex, pageSize);
        Pager pager = new Pager();
        pager.setPageIndex(pageIndex);
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<GlassesDeviceBo> staffBos = mapperFacade.mapAsList(page.getContent(), GlassesDeviceBo.class);
        pager.setData(staffBos);
        return pager;
    }

    @Override
    public void save(GlassesDeviceBo cmdBo) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(GlassesDeviceBo.class,GlassesDevice.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        GlassesDevice staff = mapperFacade.map(GlassesDeviceBo.class, GlassesDevice.class);
        glassesDeviceDao.save(staff);
    }

    @Override
    public void deleteByIds(String ids) {
        glassesDeviceDao.deleteById(ids);
    }
}
