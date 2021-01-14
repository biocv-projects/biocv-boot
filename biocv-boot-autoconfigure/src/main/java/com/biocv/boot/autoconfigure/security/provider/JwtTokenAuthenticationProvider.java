package com.biocv.boot.autoconfigure.security.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.biocv.boot.autoconfigure.auth.dao.AuthPermissionRepository;
import com.biocv.boot.autoconfigure.auth.dao.AuthRoleRepository;
import com.biocv.boot.autoconfigure.auth.model.AuthPermission;
import com.biocv.boot.autoconfigure.auth.model.AuthRole;
import com.biocv.boot.autoconfigure.security.bean.JwtTokenAuthentication;
import com.biocv.boot.autoconfigure.security.service.JwtUserService;
import com.biocv.boot.autoconfigure.security.userDetails.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author kai
 * @date 2020/9/28 18:29
 */
//@Component
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    private JwtUserService jwtUserService;

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

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
    public JwtTokenAuthenticationProvider(JwtUserService jwtUserService){
        this.jwtUserService = jwtUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(JwtTokenAuthentication.class, authentication,
                () ->
                        "Only JwtAuthenticationToken is supported");
        DecodedJWT token = ((JwtTokenAuthentication) authentication).getToken();
        //校验超时与否
        if(token.getExpiresAt().before(Calendar.getInstance().getTime())){
            throw new NonceExpiredException("Token expires");
        }

        String subject = token.getSubject();
//        UserDetails user = jwtUserService.getUserLoginInfo(userName);
//        if(user == null || user.getPassword()==null)
//        {
//            throw new NonceExpiredException("Token expires");
//        }

        //校验
        String encryptSalt = token.getAlgorithm();
        encryptSalt = "123456ef";
        try {
            Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(subject)
                    .build();
            DecodedJWT verify = verifier.verify(token.getToken());
            subject = verify.getSubject();
        } catch (Exception e) {
            throw new BadCredentialsException("JWT token verify fail", e);
        }

        //TODO 根据角色查询权限，然后设置回去，向链条后传递
        //用于在successHandler拿到做处理
        JSONObject jsonObject = JSONObject.parseObject(subject);
        String userName = jsonObject.getString("userName");
        JSONArray roleSet = jsonObject.getJSONArray("roles");

        HashSet<String> roles = new HashSet<>();
        for (int i = 0; i < roleSet.size(); i ++){
            String roleStr = roleSet.getString(i);
            roles.add(roleStr);
        }


        //TODO 这里应该是根据角色查出来的权限列表
        Collection<UserInfo.Autoriztion> autoriztions = new ArrayList<>();
        for (String roleCode : roles){
            AuthRole authRole = authRoleRepository.findByCode(roleCode);
            if (authRole != null){
                Set<AuthPermission> authPermissionSet = authRole.getAuthPermissionSet();
                for (AuthPermission authPermission: authPermissionSet){
                    String authorizationCode = authPermission.getCode();
                    UserInfo.Autoriztion autoriztion = new UserInfo.Autoriztion();
                    autoriztion.setAuthorizition(authorizationCode);
                    autoriztions.add(autoriztion);
                }
            }
        }

        //超级用户,添加上所有的权限.
        if (jsonObject.getBoolean("isRoot")){
            List<AuthPermission> authPermissions = authPermissionRepository.findAll();
            for (AuthPermission authPermission : authPermissions){
                String authorizationCode = authPermission.getCode();
                UserInfo.Autoriztion autoriztion = new UserInfo.Autoriztion();
                autoriztion.setAuthorizition(authorizationCode);
                autoriztions.add(autoriztion);
            }
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
//        userInfo.setRoleSet(roles);
        userInfo.setAuthorities(autoriztions);
        JwtTokenAuthentication jwtTokenAuthentication = new JwtTokenAuthentication(userInfo, null, token,autoriztions);
        return jwtTokenAuthentication;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtTokenAuthentication.class
                .isAssignableFrom(authentication));
    }
}
