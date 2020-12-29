package com.biocv.boot.autoconfigure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.web.BioCVResult;
import com.biocv.boot.web.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
public class AuthenticateFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(401);
        BioCVResult bioCVResult = BioCVResult.fail(ResultCode.AUTHENTICATION_FAIL);
        bioCVResult.setMessage(ResultCode.AUTHENTICATION_FAIL.getMessage() + ":" +  exception.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSON(bioCVResult).toString());
    }
}
