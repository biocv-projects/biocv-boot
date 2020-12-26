package com.biocv.boot.autoconfigure.security.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.biocv.boot.autoconfigure.security.config.JwtAuthenticationToken;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Calendar;

/**
 * @author kai
 * @date 2020/9/28 18:29
 */
//@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private JwtUserService jwtUserService;

    /**
     * 构造方法
     *
     * @param jwtUserService
     * @return
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-04 14:52
     * @since 1.0.0
    */
    public TokenAuthenticationProvider(JwtUserService jwtUserService){
        this.jwtUserService = jwtUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication,
                () ->
                        "Only JwtAuthenticationToken is supported");
        DecodedJWT token = ((JwtAuthenticationToken) authentication).getToken();
        //校验超时与否
        if(token.getExpiresAt().before(Calendar.getInstance().getTime())){
            throw new NonceExpiredException("Token expires");
        }

        String userName = token.getSubject();
        UserDetails user = jwtUserService.getUserLoginInfo(userName);
        if(user == null || user.getPassword()==null)
        {
            throw new NonceExpiredException("Token expires");
        }

        //校验
        String encryptSalt = user.getPassword();
        try {
            Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(userName)
                    .build();
            verifier.verify(token.getToken());
        } catch (Exception e) {
            throw new BadCredentialsException("JWT token verify fail", e);
        }

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(user, null, token,user.getAuthorities());
        return jwtAuthenticationToken;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
