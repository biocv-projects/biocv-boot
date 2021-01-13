package com.biocv.boot.autoconfigure.auth;

import com.biocv.boot.autoconfigure.auth.model.AuthUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
//@EntityScan(basePackageClasses = AuthUser.class)
//@EnableJpaRepositories(basePackages = "com.biocv.boot.autoconfigure.auth")
public class AuthAutoConfiguration  {


    @Bean
    public AuthDataInit authDataInit(){
        return new AuthDataInit();
    }
}
