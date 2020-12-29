package com.biocv.boot.autoconfigure.security;

import com.biocv.boot.autoconfigure.security.config.AuthenticationConfigurer;
import com.biocv.boot.autoconfigure.security.config.JwtVerifyConfigurer;
import com.biocv.boot.autoconfigure.security.handler.Authentication403EntryPoint;
import com.biocv.boot.autoconfigure.security.handler.JwtVerifySuccessHandler;
import com.biocv.boot.autoconfigure.security.handler.AuthenticateSuccessHandler;
import com.biocv.boot.autoconfigure.security.provider.JwtTokenAuthenticationProvider;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
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
        Authentication403EntryPoint.class,
//        BioCVAccessDeniedHandler.class,
        JwtVerifySuccessHandler.class,
        AuthenticateSuccessHandler.class,
        JwtTokenAuthenticationProvider.class,
        JwtUserService.class
//        LoginController.class
})
@ConditionalOnClass({AuthenticationManager.class })
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticateSuccessHandler authenticateSuccessHandler;

    @Autowired
    private JwtVerifySuccessHandler jwtVerifySuccessHandler;

    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider;

    @Autowired
    private Authentication403EntryPoint authentication403EntryPoint;

//    @Autowired
//    private BioCVAccessDeniedHandler bioCVAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有请求都要认证
        http = http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated()
                .and();

        //禁用session
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        //设置验证的服务
        http = http.userDetailsService(jwtUserService);

        //自定义认证异常返回数据格式
//        http = http.exceptionHandling()
//                .authenticationEntryPoint(bioCV403AuthenticationEntryPoint)
//                .accessDeniedHandler(bioCVAccessDeniedHandler)
//                .and();

        //禁用csrf
        http = http.csrf().disable();

        //禁用匿名访问
//        http = http.anonymous().disable();

        //添加返回头
        http = http.headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and();

        http = http.formLogin().disable();

        //添加登录过滤器
        http = http.apply(new AuthenticationConfigurer<>())
                .authenticationSuccessHandler(authenticateSuccessHandler)
                .and();

        //添加校验token过滤器
        http.apply(new JwtVerifyConfigurer<>())
                .tokenValidSuccessHandler(jwtVerifySuccessHandler)
                .and();

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
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(daoAuthenticationProvider())
//                auth.authenticationProvider(jwtTokenAuthenticationProvider);
//    }

//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(jwtUserService);
//        return authenticationProvider;
//    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

}
