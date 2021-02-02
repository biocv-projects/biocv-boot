package com.biocv.boot.vis.cmd.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.autoconfigure.BaseController;
import com.biocv.boot.vis.cmd.bo.CmdBo;
import com.biocv.boot.vis.cmd.service.CmdService;
import com.biocv.boot.web.BioCVResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * cmd 接口路由
 *
 * @author Tyler.Feng
 * @date 2021-01-29 14:48
 * @since 1.0.0
 */
@RequestMapping("/cmd")
@RestController
public class CmdController extends BaseController {

    @Autowired
    private CmdService cmdService;

//    @PreAuthorize("hasAuthority('cmd:api:list')")
    @PostMapping("/list")
    @CrossOrigin
//    @DbLog
    public Pager list(@RequestBody(required = false) CmdBo condition){
        if (condition == null){
            condition = new CmdBo();
        }
//        Pager pager = cmdService.getPagerByCondition(condition, getPageIndex(), getPageSize());
        Pager pager = cmdService.getPagerByCondition(condition, condition.getPageIndex() - 1, condition.getPageSize());
        return pager;
    }

    @PreAuthorize("hasAuthority('cmd:api:save')")
    @PostMapping("/save")
    public BioCVResult save(@RequestBody CmdBo bo){
        cmdService.save(bo);
        return BioCVResult.success(bo);
    }

    @PreAuthorize("hasAuthority('cmd:api:delete')")
    @DeleteMapping("/delete")
    public BioCVResult delete(String id){
        cmdService.deleteByIds(id);
        return BioCVResult.success(id);
    }

    @DeleteMapping("/clear")
    public BioCVResult clear(){
        cmdService.clear();
        return BioCVResult.success();
    }

}
