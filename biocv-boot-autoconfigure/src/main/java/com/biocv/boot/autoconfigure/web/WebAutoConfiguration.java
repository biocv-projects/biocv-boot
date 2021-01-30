package com.biocv.boot.autoconfigure.web;

import com.biocv.boot.autoconfigure.web.util.FileUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.io.File;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * a web auto configuration
 *
 * @author kai
 * @date 2020/12/17 16:00
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(WebProperties.class)
@Import({ExceptionAdvice.class})
public class WebAutoConfiguration implements WebMvcConfigurer {

    /**
     * web配置参数
    */
    private final WebProperties webProperties;

    public WebAutoConfiguration(WebProperties webProperties){ this.webProperties = webProperties; }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        Locale locale = new Locale("en","US");
        localeResolver.setDefaultLocale(locale);
        localeResolver.setCookieName("l");
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("l");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加国际化拦截器
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * 跨域设置
     *
     * @param registry
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-29 13:58
     * @since 1.0.0
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    /**
     * 图片资源映射
     *
     * @param registry
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-29 13:58
     * @since 1.0.0
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + new File(FileUtil.systemFilePath).getAbsolutePath() + "/upload/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
    }
}
