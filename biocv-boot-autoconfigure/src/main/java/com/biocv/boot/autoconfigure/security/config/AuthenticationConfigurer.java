package com.biocv.boot.autoconfigure.security.config;

import com.biocv.boot.autoconfigure.security.filter.AuthenticationFilter;
import com.biocv.boot.autoconfigure.security.handler.AuthenticateFailHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * 认证filter
 * @author kai
 * @date 2020/9/28 15:20
 */
//@Deprecated
public final class AuthenticationConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<AuthenticationConfigurer<H>, H> {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(H http) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        //设置Filter使用的AuthenticationManager,这里取公共的即可
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置失败的Handler
        authenticationFilter.setAuthenticationFailureHandler(new AuthenticateFailHandler());
        authenticationFilter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler);
        //不将认证后的context放入session
//        authenticationFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
//        MyUsernameAndPasswordAuthenticationFilter filter = postProcess(authenticationFilter);
        //指定Filter的位置
        http.addFilterAfter(authenticationFilter, LogoutFilter.class);
    }

    /**
     * TODO
     *
     * @param authenticationSuccessHandler
     * @return com.biocv.security.config.JsonLoginConfigurer<H>
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-09-28 15:51
     * @since 1.0.0
    */
    public AuthenticationConfigurer<H> authenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        return this;
    }

}
