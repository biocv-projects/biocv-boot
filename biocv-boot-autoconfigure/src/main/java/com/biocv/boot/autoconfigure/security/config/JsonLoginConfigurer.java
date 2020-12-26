package com.biocv.boot.autoconfigure.security.config;

import com.biocv.boot.autoconfigure.security.filter.MyUsernameAndPasswordAuthenticationFilter;
import com.biocv.boot.autoconfigure.security.handler.LoginFailHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 配置登录的filter
 * @author kai
 * @date 2020/9/28 15:20
 */
public final class JsonLoginConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<JsonLoginConfigurer<H>, H> {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(H http) {
        MyUsernameAndPasswordAuthenticationFilter authenticationFilter = new MyUsernameAndPasswordAuthenticationFilter();
        //设置Filter使用的AuthenticationManager,这里取公共的即可
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置失败的Handler
        authenticationFilter.setAuthenticationFailureHandler(new LoginFailHandler());
        authenticationFilter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler);
        //不将认证后的context放入session
        authenticationFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
        MyUsernameAndPasswordAuthenticationFilter filter = postProcess(authenticationFilter);
        //指定Filter的位置
        http.addFilterAfter(filter, LogoutFilter.class);
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
    public JsonLoginConfigurer<H> authenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        return this;
    }

}
