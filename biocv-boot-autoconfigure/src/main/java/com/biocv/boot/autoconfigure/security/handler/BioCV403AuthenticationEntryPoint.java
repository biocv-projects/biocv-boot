package com.biocv.boot.autoconfigure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.web.BioCVResult;
import com.biocv.boot.web.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证访问资源的处理入口
 * 因为没有携带认证信息的的话，会放过所有的Filter到FilterInterceptor，然后抛出AuthenticationException
 * 认证失败，来到下面方法处理
 *
 * @author kai
 * @date 2020/10/24 13:55
 */
//@Component
public class BioCV403AuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        BioCVResult bioCVResult = BioCVResult.fail(ResultCode.FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSON(bioCVResult).toString());
    }
}
