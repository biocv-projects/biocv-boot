package com.biocv.boot.vis.staff.controller;

import com.biocv.boot.Pager;
import com.biocv.boot.vis.staff.bo.StaffBo;
import com.biocv.boot.vis.staff.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/list")
    public Pager list(){
        Pager pager = staffService.getPagerByCondition(new StaffBo(), 0, 10);
        return pager;
    }

}
