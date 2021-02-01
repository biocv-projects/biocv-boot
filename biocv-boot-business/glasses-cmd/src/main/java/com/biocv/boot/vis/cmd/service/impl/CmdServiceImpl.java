package com.biocv.boot.vis.cmd.service.impl;

import com.biocv.boot.Pager;
import com.biocv.boot.pojo.BaseBo;
import com.biocv.boot.vis.cmd.bo.CmdBo;
import com.biocv.boot.vis.cmd.dao.CmdDao;
import com.biocv.boot.vis.cmd.model.Cmd;
import com.biocv.boot.vis.cmd.service.CmdService;
import com.biocv.protocol.CmdEvent;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * cmd service 实现
 *
 * @author Tyler.Feng
 * @date 2021-01-29 14:45
 * @since 1.0.0
 */
@Service
@Transactional
public class CmdServiceImpl implements CmdService, ApplicationListener<CmdEvent> {

    @Autowired
    private CmdDao cmdDao;

    @Override
    public Pager getPagerByCondition(BaseBo condition, int pageIndex, int pageSize) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Cmd.class,CmdBo.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Page<Cmd> page = cmdDao.findByPage(condition, pageIndex, pageSize);
        Pager pager = new Pager();
        pager.setPageIndex(pageIndex);
        pager.setPageSize(page.getTotalPages());
        pager.setPageSize(page.getSize());
        pager.setTotal(page.getTotalElements());
        List<CmdBo> staffBos = mapperFacade.mapAsList(page.getContent(), CmdBo.class);
        pager.setData(staffBos);
        return pager;
    }

    @Override
    public void save(CmdBo cmdBo) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(CmdBo.class,Cmd.class).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Cmd staff = mapperFacade.map(cmdBo, Cmd.class);
        cmdDao.save(staff);
    }

    @Override
    public void deleteByIds(String ids) {
        cmdDao.deleteById(ids);
    }

    /**
     * 处理设备上传命令
     */
    @Override
    public void onApplicationEvent(CmdEvent event) {
        CmdBo cmdBo = new CmdBo();
        cmdBo.setRequestStr(event.getContent());
        cmdBo.setRequestTime(System.currentTimeMillis());
        //保存
        this.save(cmdBo);
    }
}
