package com.biocv.boot.autoconfigure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.web.BioCVResult;
import com.biocv.boot.web.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限不足Handler
 *
 * @author kai
 * @date 2020/10/24 13:40
 */
//@Component
public class BioCVAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        BioCVResult bioCVResult = BioCVResult.fail(ResultCode.FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSON(bioCVResult).toString());
//        response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Access Denied");
    }
}
