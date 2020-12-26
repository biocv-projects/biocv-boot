package com.biocv.boot.autoconfigure.security.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.biocv.boot.autoconfigure.security.config.JwtAuthenticationToken;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt的认证成功处理逻辑
 * @author kai
 * @date 2020/10/10 09:34
 */
//@Component
public class JwtRefreshTokenHandler implements AuthenticationSuccessHandler {

    //刷新间隔5分钟
    private static final int tokenRefreshInterval = 300;

    @Autowired
    private JwtUserService jwtUserService;

//    public JwtRefreshTokenHandler(JwtUserService jwtUserService) {
//        this.jwtUserService = jwtUserService;
//    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DecodedJWT token = ((JwtAuthenticationToken) authentication).getToken();
        boolean shouldRefresh = shouldTokenRefresh(token.getIssuedAt());
        if(shouldRefresh) {
            String newToken = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
            response.setHeader("Authorization", newToken);
        }
    }

    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
