package com.biocv.controller;

import com.biocv.boot.autoconfigure.logging.DbLog;
import com.biocv.boot.exception.BasicException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kai
 * @date 2020/12/23 20:43
 */
@RestController
public class TestController {

//    @PreAuthorize("hasPermission('auth:user:query')")
//    @PreAuthorize("hasAuthority('auth:user:query')")
    @PostMapping("/test/response")
    @DbLog
    public Map<String, String> response(@RequestBody Object o){
//        throw new BasicException("fuck");
        System.out.println("执行代码");
        Map<String,String> returnV = new HashMap<>();
        returnV.put("return","value");
        throw new BasicException("沃日");
//        return returnV;
    }
}
