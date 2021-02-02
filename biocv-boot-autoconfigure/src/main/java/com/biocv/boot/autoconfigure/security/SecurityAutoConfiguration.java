package com.biocv.boot.autoconfigure.security;

import com.biocv.boot.autoconfigure.security.config.AuthenticationConfigurer;
import com.biocv.boot.autoconfigure.security.config.JwtVerifyConfigurer;
import com.biocv.boot.autoconfigure.security.handler.AuthenticateSuccessHandler;
import com.biocv.boot.autoconfigure.security.handler.Authentication403EntryPoint;
import com.biocv.boot.autoconfigure.security.handler.JwtVerifySuccessHandler;
import com.biocv.boot.autoconfigure.security.provider.JwtTokenAuthenticationProvider;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import com.biocv.boot.autoconfigure.security.support.UserSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author kai
 * @date 2020/9/28 16:00
 */
@Configuration
//TODO 这个怎么动态生效？需要实现如果没有UserSupport 整个 spring security都不生效的效果
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({
        Authentication403EntryPoint.class,
        JwtVerifySuccessHandler.class,
        AuthenticateSuccessHandler.class,
        JwtTokenAuthenticationProvider.class,
        JwtUserService.class
})
@ConditionalOnClass({AuthenticationManager.class })
//有实现才加载这个配置
@ConditionalOnBean(UserSupport.class)
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticateSuccessHandler authenticateSuccessHandler;

    @Autowired
    private JwtVerifySuccessHandler jwtVerifySuccessHandler;

    @Autowired
    private JwtUserService jwtUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有请求都要认证
        http = http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger**/**").permitAll()
                .antMatchers(HttpMethod.GET,"/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET,"/v3/**").permitAll()
                .antMatchers(HttpMethod.GET,"/doc.html").permitAll()
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

        //禁用cors
        //使用springmvc的cors
        http = http.cors(withDefaults());

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

}
