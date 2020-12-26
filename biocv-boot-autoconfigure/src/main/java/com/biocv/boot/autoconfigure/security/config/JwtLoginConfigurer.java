package com.biocv.boot.autoconfigure.security.config;

import com.biocv.boot.autoconfigure.security.filter.JwtTokenFilter;
import com.biocv.boot.autoconfigure.security.handler.LoginFailHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * jwt 配置
 *
 * @author kai
 * @date 2020/10/4 15:25
 */
public final class JwtLoginConfigurer <H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<JwtLoginConfigurer<H>, H> {

    private JwtTokenFilter authFilter = new JwtTokenFilter();

    @Override
    public void configure(H http) throws Exception {
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authFilter.setAuthenticationFailureHandler(new LoginFailHandler());

        JwtTokenFilter jwtTokenFilter = postProcess(authFilter);
        http.addFilterBefore(jwtTokenFilter, LogoutFilter.class);

    }

    /**
     * 设置filter的successHandler
     *
     * @param successHandler
     * @return com.biocv.security.config.JwtLoginConfigurer
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-10 09:29
     * @since 1.0.0
    */
    public JwtLoginConfigurer<H> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
        authFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }
}
