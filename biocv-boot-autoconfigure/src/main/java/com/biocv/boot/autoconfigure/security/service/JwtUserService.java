package com.biocv.boot.autoconfigure.security.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.biocv.boot.autoconfigure.security.userDetails.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * 查询数据库检查用户
 */
public class JwtUserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

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
        //BCrypt.gensalt();  正式开发时可以调用该方法实时生成加密的salt
        String salt = "123456ef";
        Algorithm algorithm = Algorithm.HMAC256(salt);
        //设置1小时后过期
        Date date = new Date(System.currentTimeMillis()+ 3600 * 1000);
        JSONObject subjectJson = new JSONObject();
        subjectJson.put("userName",user.getUsername());
        JSONArray rolesArray = new JSONArray();
        rolesArray.add("sa");
        rolesArray.add("operator");
        rolesArray.add("visitor");
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

        //测试数据
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("admin");
        userInfo.setPassword("{noop}admin");

        return userInfo;

    }

}
