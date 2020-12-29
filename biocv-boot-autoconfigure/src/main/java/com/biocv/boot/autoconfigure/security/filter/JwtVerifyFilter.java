package com.biocv.boot.autoconfigure.security.filter;

import com.auth0.jwt.JWT;
import com.biocv.boot.autoconfigure.security.bean.JwtTokenAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验jwt token的过滤器，配置在{最前面,logOut之前}位置
 *
 * @author kai
 * @date 2020/9/28 17:41
 */
public class JwtVerifyFilter extends OncePerRequestFilter {

    //认证管理器
    private AuthenticationManager authenticationManager;

    //匹配的请求
    private RequestMatcher requiresAuthenticationRequestMatcher;

    //成功的处理器
    private AuthenticationSuccessHandler successHandler;

    //失败的处理器
    private AuthenticationFailureHandler failureHandler;

    public JwtVerifyFilter(){
        requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //header没带token的，直接放过，因为部分url匿名用户也可以访问
        //如果需要不支持匿名用户的请求没带token，这里放过也没问题，因为SecurityContext中没有认证信息，后面会被权限控制模块拦截
        if (!requiresAuthentication(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authResult;

        //从头中获取token并封装后提交给AuthenticationManager
        String token = getJwtToken(request);
        try {
            JwtTokenAuthentication authToken = new JwtTokenAuthentication(JWT.decode(token));
            authResult = this.getAuthenticationManager().authenticate(authToken);

        }catch (AuthenticationException failed){
            // Authentication failed
            unsuccessfulAuthentication(request, response, failed);

            return;
        }

        successfulAuthentication(request,response,filterChain,authResult);

        filterChain.doFilter(request,response);

    }

    /**
     * 根据matcher查看是不是要过滤这次请求，这里带Authorization的都要过滤处理
     *
     * @param request
     * @param response
     * @return boolean
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-09-28 17:52
     * @since 1.0.0
    */
    protected boolean requiresAuthentication(HttpServletRequest request,
                                             HttpServletResponse response) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    /**
     * 拿出请求头上的token
     *
     * @param request
     * @return java.lang.String
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-09-28 17:58
     * @since 1.0.0
    */
    private String getJwtToken(HttpServletRequest request) {
        String authInfo = request.getHeader("Authorization");
        return StringUtils.removeStart(authInfo, "Bearer ");
    }

    /**
     * 认证成功
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-10 08:54
     * @since 1.0.0
    */
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);


        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * 处理认证失败的情况
     * Default behaviour for unsuccessful authentication.
     * <ol>
     * <li>Clears the {@link SecurityContextHolder}</li>
     * <li>Stores the exception in the session (if it exists or
     * <tt>allowSesssionCreation</tt> is set to <tt>true</tt>)</li>
     * <li>Informs the configured <tt>RememberMeServices</tt> of the failed login</li>
     * <li>Delegates additional behaviour to the {@link AuthenticationFailureHandler}.</li>
     * </ol>
     */
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * 获取认证管理器
     *
     * @return org.springframework.security.authentication.AuthenticationManager
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-09 16:53
     * @since 1.0.0
    */
    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * 设置认证管理器
     *
     * @param authenticationManager
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-09 16:52
     * @since 1.0.0
    */
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 设置失败处理器
     *
     * @param failureHandler
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-09 16:45
     * @since 1.0.0
    */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler){
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }

    /**
     * 设置成功处理器
     *
     * @param successHandler
     * @return void
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-10 09:29
     * @since 1.0.0
    */
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler){
        this.successHandler = successHandler;
    }
}
