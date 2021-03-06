package com.biocv.boot.autoconfigure.context;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * i18n auto configuration
 *
 * @author kai
 * @date 2020/12/17 16:39
 */
@Configuration
@Conditional(MessageSourceAutoConfiguration.ResourceBundleCondition.class)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {

    private static final Resource[] NO_RESOURCES = {};

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean
    public MessageSource messageSource(MessageSourceProperties properties) {
        //spring.messages.basename = i18/*
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        Resource[] resources = getResources(this.getClass().getClassLoader(), properties.getBasename());
        Set<String> set = new HashSet<>();
        for (Resource resource : resources){
            String filename = resource.getFilename();
            //eg. demo_en_US.properties
            String prefix = filename.split("_")[0];
            set.add(prefix);
        }
        StringBuilder baseNamesBuilder = new StringBuilder();
        for (String prefix : set){
            baseNamesBuilder.append(prefix).append(",");
        }
        String baseNames = baseNamesBuilder.toString();
        if (baseNames.length() > 0){
            baseNames = baseNames.substring(0,baseNames.length() -1);
        }
        messageSource.setBasenames(baseNames);
//        if (StringUtils.hasText(properties.getBasename())) {
//            messageSource.setBasenames(StringUtils
//                    .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
//        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }

    private Resource[] getResources(ClassLoader classLoader, String name) {
        String target = name.replace('.', '/');
        try {
            return new PathMatchingResourcePatternResolver(classLoader)
                    .getResources("classpath*:" + target + ".properties");
        }
        catch (Exception ex) {
            return NO_RESOURCES;
        }
    }

    //国际化文件是否存在的spring condition
    protected static class ResourceBundleCondition extends SpringBootCondition {

        private static final ConcurrentReferenceHashMap<String, ConditionOutcome> cache = new ConcurrentReferenceHashMap<>();

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String basename = context.getEnvironment().getProperty("spring.messages.basename", "messages");
            ConditionOutcome outcome = cache.get(basename);
            if (outcome == null) {
                outcome = getMatchOutcomeForBasename(context, basename);
                cache.put(basename, outcome);
            }
            return outcome;
        }

        private ConditionOutcome getMatchOutcomeForBasename(ConditionContext context, String basename) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("ResourceBundle");
            for (String name : StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(basename))) {
                for (Resource resource : getResources(context.getClassLoader(), name)) {
                    if (resource.exists()) {
                        return ConditionOutcome.match(message.found("bundle").items(resource));
                    }
                }
            }
            return ConditionOutcome.noMatch(message.didNotFind("bundle with basename " + basename).atAll());
        }

        private Resource[] getResources(ClassLoader classLoader, String name) {
            String target = name.replace('.', '/');
            try {
                return new PathMatchingResourcePatternResolver(classLoader)
                        .getResources("classpath*:" + target + ".properties");
            }
            catch (Exception ex) {
                return NO_RESOURCES;
            }
        }

    }

}
