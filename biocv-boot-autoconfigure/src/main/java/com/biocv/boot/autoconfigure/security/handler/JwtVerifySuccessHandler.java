package com.biocv.boot.autoconfigure.security.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.biocv.boot.autoconfigure.security.bean.JwtTokenAuthentication;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt认证成功处理器
 * 刷新token
 */
public class JwtVerifySuccessHandler implements AuthenticationSuccessHandler {

    //刷新间隔5分钟
    private static final int tokenRefreshInterval = 300;

    @Autowired
    private JwtUserService jwtUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DecodedJWT token = ((JwtTokenAuthentication) authentication).getToken();
        boolean shouldRefresh = shouldTokenRefresh(token.getIssuedAt());
        if(shouldRefresh) {
            String newToken = jwtUserService.issueToken((UserDetails)authentication.getPrincipal());
            response.setHeader("Authorization", newToken);
        }
    }

    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
