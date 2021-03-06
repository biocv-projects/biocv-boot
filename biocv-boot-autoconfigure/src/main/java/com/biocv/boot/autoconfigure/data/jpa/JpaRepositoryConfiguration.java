package com.biocv.boot.autoconfigure.data.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * a jpa configuration
 *
 * @author kai
 * @date 2020/12/18 10:24
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {"com.biocv.**.dao"})
@EntityScan(basePackages = "com.biocv.**.model")
public class JpaRepositoryConfiguration {



}
