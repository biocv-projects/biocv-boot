package com.biocv.boot.vis.device.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.autoconfigure.BaseController;
import com.biocv.boot.vis.device.bo.GlassesDeviceBto;
import com.biocv.boot.vis.device.service.GlassesDeviceService;
import com.biocv.boot.web.BioCVResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 眼睛设备controller
 *
 * @author Tyler.Feng
 * @date 2021-01-29 15:43
 * @since 1.0.0
 */
@RequestMapping("/glasses")
@RestController
public class GlassesDeviceController extends BaseController {

    @Autowired
    private GlassesDeviceService glassesDeviceService;

    @PreAuthorize("hasAuthority('cmd:api:list')")
    @PostMapping("/list")
//    @DbLog
    public Pager list(@RequestBody(required = false) GlassesDeviceBto condition){
        if (condition == null){
            condition = new GlassesDeviceBto();
        }
//        Pager pager = cmdService.getPagerByCondition(condition, getPageIndex(), getPageSize());
        Pager pager = glassesDeviceService.getPagerByCondition(condition, condition.getPageIndex() - 1, condition.getPageSize());
        return pager;
    }

    @PreAuthorize("hasAuthority('cmd:api:save')")
    @PostMapping("/save")
    public BioCVResult save(@RequestBody GlassesDeviceBto bo){
        glassesDeviceService.save(bo);
        return BioCVResult.success(bo);
    }

    @PreAuthorize("hasAuthority('cmd:api:delete')")
    @DeleteMapping("/delete")
    @CrossOrigin
    public BioCVResult delete(String id){
        glassesDeviceService.deleteByIds(id);
        return BioCVResult.success(id);
    }

}
