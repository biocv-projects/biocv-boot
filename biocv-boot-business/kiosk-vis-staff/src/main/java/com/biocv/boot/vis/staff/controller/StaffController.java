package com.biocv.boot.vis.staff.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.autoconfigure.BaseController;
import com.biocv.boot.autoconfigure.web.util.FileUtil;
import com.biocv.boot.vis.staff.bo.StaffBto;
import com.biocv.boot.vis.staff.service.StaffService;
import com.biocv.boot.web.BioCVResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
//    @DbLog
    public Pager list(@RequestBody(required = false) StaffBto condition){
        if (condition == null){
            condition = new StaffBto();
        }
        Pager pager = staffService.getPagerByCondition(condition, getPageIndex(), getPageSize());
        return pager;
    }

    @PreAuthorize("hasAuthority('staff:api:save')")
    @PostMapping("/save")
    public BioCVResult save(@RequestBody StaffBto bo){
        staffService.save(bo);
        return BioCVResult.success(bo);
    }

    @PreAuthorize("hasAuthority('staff:api:delete')")
    @DeleteMapping("/delete")
    @CrossOrigin
    public BioCVResult delete(String id){
        staffService.deleteByIds(id);
        return BioCVResult.success(id);
    }

    @PostMapping("/upload")
    public BioCVResult uploadPic(@RequestParam("file") MultipartFile file){
        try{
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                // store the bytes somewhere
                String path = FileUtil.saveFileToServer("staff", "staff", file.getOriginalFilename(), file);
                return BioCVResult.success(path);
            }
        }catch (IOException e){
        }
        return BioCVResult.success("upload success");
    }

}
