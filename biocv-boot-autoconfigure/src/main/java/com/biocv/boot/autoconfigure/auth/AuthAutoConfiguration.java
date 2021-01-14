package com.biocv.boot.autoconfigure.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 权限自动配置
 *
 * @author Tyler.Feng
 * @date 2021-01-12 10:43
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(DataSource.class)
public class AuthAutoConfiguration  {

    @Bean
    public AuthDataInit authDataInit(){
        return new AuthDataInit();
    }

    @Bean
    public AuthPermissionService authPermissionService(){
        return new AuthPermissionService();
    }

}
