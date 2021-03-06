package com.biocv.boot.autoconfigure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kai
 * @date 2020/9/28 15:17
 */
//@Component
public class AuthenticateSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUserService jwtUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //认证成功，生成token返回
        String token = jwtUserService.issueToken((UserDetails)authentication.getPrincipal());
        response.setHeader("Authorization", token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",HttpStatus.OK.value());
        jsonObject.put("status", HttpStatus.OK);
        jsonObject.put("msg","Authentication Success");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

}
