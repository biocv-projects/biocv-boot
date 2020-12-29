package com.biocv.boot.autoconfigure.security.config;

import com.biocv.boot.autoconfigure.security.filter.JwtVerifyFilter;
import com.biocv.boot.autoconfigure.security.handler.AuthenticateFailHandler;
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
public final class JwtVerifyConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<JwtVerifyConfigurer<H>, H> {

    private JwtVerifyFilter jwtVerifyFilter = new JwtVerifyFilter();

    @Override
    public void configure(H http) throws Exception {
        jwtVerifyFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        jwtVerifyFilter.setAuthenticationFailureHandler(new AuthenticateFailHandler());

        JwtVerifyFilter jwtVerifyFilter = postProcess(this.jwtVerifyFilter);
        http.addFilterBefore(jwtVerifyFilter, LogoutFilter.class);

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
    public JwtVerifyConfigurer<H> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
        jwtVerifyFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }
}
