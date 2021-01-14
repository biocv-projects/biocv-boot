package com.biocv.boot.vis.staff.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.autoconfigure.BaseController;
import com.biocv.boot.autoconfigure.logging.DbLog;
import com.biocv.boot.vis.staff.bo.StaffBo;
import com.biocv.boot.vis.staff.service.StaffService;
import com.biocv.boot.web.BioCVResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * staff controller
 *
 * @author Tyler.Feng
 * @date 2021-01-14 11:18
 * @since 1.0.0
 */
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @PreAuthorize("hasAuthority('staff:api:list')")
    @PostMapping("/list")
    @DbLog
    public Pager list(@RequestBody StaffBo condition){
        Pager pager = staffService.getPagerByCondition(condition, getPageIndex(), getPageSize());
        return pager;
    }

    @PreAuthorize("hasAuthority('staff:api:save')")
    @PostMapping("/save")
    public BioCVResult save(@RequestBody StaffBo bo){
        staffService.save(bo);
        return BioCVResult.success(bo);
    }

    @PreAuthorize("hasAuthority('staff:api:delete')")
    @DeleteMapping()
    public BioCVResult delete(String id){
        staffService.deleteByIds(id);
        return BioCVResult.success(id);
    }

}
