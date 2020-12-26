package com.biocv.boot.autoconfigure.logging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 日志自动配置
 *
 * @author kai
 * @date 2020/12/25 15:28
 */
@Configuration
@Import({LogAspect.class,LogDbAspect.class})
public class LogginAutoConfiguration {

    /**
     * 上下文
     */
    private final ApplicationContext applicationContext;

    public LogginAutoConfiguration(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

}
