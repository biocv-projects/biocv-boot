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
 * @author kai
 * @date 2020/9/28 15:19
 */
public class LoginFailHandler implements AuthenticationFailureHandler {

    /**
     * 认证失败，返回401错误
     *
     * @param request
     * @param response
     * @param exception
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-09-28 15:19
     * @since 1.0.0
    */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status",HttpStatus.UNAUTHORIZED);
//        jsonObject.put("msg","登录失败");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(jsonObject.toString());
//        response.setStatus(HttpStatus.FORBIDDEN.value());
//        BioCVResult bioCVResult = BioCVResult.fail(ResultCode.FORBIDDEN);
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(JSONObject.toJSON(bioCVResult).toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        BioCVResult bioCVResult = BioCVResult.fail(ResultCode.AUTHENTICATION_FAIL);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSON(bioCVResult).toString());
    }
}
