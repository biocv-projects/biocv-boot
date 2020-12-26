package com.biocv.boot.autoconfigure.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * web properties
 *
 * @author kai
 * @date 2020/12/18 14:47
 */
@ConfigurationProperties(prefix = "biocv.web")
public class WebProperties {

    /**
     * 默认语言 eg. en_US
     */
    private String defaultLanguage;


    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}
