package com.biocv;

import com.biocv.boot.autoconfigure.web.EnableBiocvWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author kai
 * @date 2020/12/19 23:03
 */
@SpringBootApplication
@EnableBiocvWeb
//@ComponentScans({@ComponentScan(basePackages = "com.biocv")})
@EnableJpaRepositories(basePackages = "com.biocv")
@EntityScan(basePackages = "com.biocv")
public class TestWebApp {

    public static void main(String[] args) {
        new SpringApplication(TestWebApp.class).run(args);
    }

}
