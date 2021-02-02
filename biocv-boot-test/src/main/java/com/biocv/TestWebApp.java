package com.biocv;

import com.biocv.boot.autoconfigure.web.EnableBiocvWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kai
 * @date 2020/12/19 23:03
 */
@SpringBootApplication
//@ComponentScans({@ComponentScan(basePackages = "com.biocv")})
//@EnableJpaRepositories(basePackages = "com.biocv")
//@EntityScan(basePackages = "com.biocv")
@EnableAsync//注意这里，这个注解启用了线程池
public class TestWebApp {

    public static void main(String[] args) {
        new SpringApplication(TestWebApp.class).run(args);
    }

}
