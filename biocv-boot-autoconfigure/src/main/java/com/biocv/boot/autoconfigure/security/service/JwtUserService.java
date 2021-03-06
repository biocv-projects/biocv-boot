package com.biocv.boot.autoconfigure.security.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.biocv.boot.autoconfigure.security.support.UserSupport;
import com.biocv.boot.autoconfigure.security.support.IAuthUser;
import com.biocv.boot.autoconfigure.security.userDetails.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Set;

/**
 * 查询数据库检查用户
 */
public class JwtUserService implements UserDetailsService {

//    @Autowired
//    private AuthUserRepository authUserRepository;

    @Autowired
    private UserSupport userSupport;

    private final PasswordEncoder passwordEncoder;

    public JwtUserService() {
        //默认使用 bcrypt， strength=10
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public UserDetails getUserLoginInfo(String username) {
        String salt = "123456ef";
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
    }

    /**
     * 颁发证书
     */
    public String issueToken(UserDetails user) {
        UserInfo userInfo = (UserInfo) user;
//        String gensalt = BCrypt.gensalt(); //       正式开发时可以调用该方法实时生成加密的salt
        String salt = "123456ef";
        Algorithm algorithm = Algorithm.HMAC256(salt);
        //设置1小时后过期
        Date date = new Date(System.currentTimeMillis()+ 3600 * 1000);
        JSONObject subjectJson = new JSONObject();
        subjectJson.put("userName",user.getUsername());
        subjectJson.put("isRoot", userInfo.isRoot());
        JSONArray rolesArray = new JSONArray();
        Set<String> roleSet = userInfo.getRoleSet();
        if(roleSet != null){
            for (String roleCode : roleSet){
                rolesArray.add(roleCode);
            }
        }
        subjectJson.put("roles",rolesArray);
        String subject = subjectJson.toJSONString();
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 查数据库，找出这个人的信息返回出去
        IAuthUser authUser = userSupport.getUserByUserName(username);
//        AuthUser authUser = authUserRepository.findByUserName(username);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(authUser.getUserName());
        userInfo.setPassword("{noop}" + authUser.getPassword());
        userInfo.setRoot(authUser.isRoot());
//        Set<AuthRole> authRoleSet = authUser.getAuthRoleSet();
//        Set<String> roles = new HashSet<>();
//        for (AuthRole authRole : authRoleSet){
//            roles.add(authRole.getCode());
//        }
        Set<String> roleSet = authUser.getRoleSet();
        userInfo.setRoleSet(roleSet);
        return userInfo;

    }

}
