package com.biocv.boot.autoconfigure.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

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
}
