package com.biocv.boot.autoconfigure.security;

import com.biocv.boot.autoconfigure.security.config.JsonLoginConfigurer;
import com.biocv.boot.autoconfigure.security.config.JwtLoginConfigurer;
import com.biocv.boot.autoconfigure.security.handler.BioCV403AuthenticationEntryPoint;
import com.biocv.boot.autoconfigure.security.handler.BioCVAccessDeniedHandler;
import com.biocv.boot.autoconfigure.security.handler.JwtRefreshTokenHandler;
import com.biocv.boot.autoconfigure.security.handler.LoginSuccessHandler;
import com.biocv.boot.autoconfigure.security.provider.TokenAuthenticationProvider;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import com.biocv.boot.autoconfigure.security.userDetails.JwtUser;
import com.biocv.boot.autoconfigure.web.CustomErrorType;
import com.biocv.boot.autoconfigure.web.ExceptionAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Arrays;

/**
 * @author kai
 * @date 2020/9/28 16:00
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({
        BioCV403AuthenticationEntryPoint.class,
        BioCVAccessDeniedHandler.class,
        JwtRefreshTokenHandler.class,
        LoginSuccessHandler.class,
        TokenAuthenticationProvider.class,
        JwtUserService.class
})
@ConditionalOnClass({AuthenticationManager.class })
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private JwtRefreshTokenHandler jwtRefreshTokenHandler;

    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private BioCV403AuthenticationEntryPoint bioCV403AuthenticationEntryPoint;

    @Autowired
    private BioCVAccessDeniedHandler bioCVAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/authUser/query").permitAll()
                .anyRequest().authenticated()
//                .and().exceptionHandling().accessDeniedHandler(bioCVAccessDeniedHandler)
                .and().exceptionHandling().authenticationEntryPoint(bioCV403AuthenticationEntryPoint).accessDeniedHandler(bioCVAccessDeniedHandler)
//                .and().exceptionHandling().authenticationEntryPoint(bioCV403AuthenticationEntryPoint)
                .and()
//                .csrf().disable()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and()
                .apply(new JsonLoginConfigurer<>()).authenticationSuccessHandler(loginSuccessHandler)
                .and()
                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshTokenHandler)
                .and()
//                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshTokenHandler)
//                .sessionManagement().disable();
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().anonymous().disable();
    }

    /**
     * 配置provider
     *
     * @param auth
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-10 11:28
     * @since 1.0.0
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(jwtUserService);
        return authenticationProvider;
    }

}
