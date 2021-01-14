package com.biocv.boot.autoconfigure.orika;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * orika bean copy自动配置
 *
 * @author Tyler.Feng
 * @date 2021-01-14 18:21
 * @since 1.0.0
 */
@Configuration
//@ConditionalOnClass(MapperFactory.class)
public class OrikaAutoConfiguration {

    @Bean
    public ConfigurableMapper configurableMapper(List<Mapper> mappers){

        return new ConfigurableMapper(){
            @Override
            protected void configure(MapperFactory factory) {
                for (Mapper mapper : mappers){
                    factory.registerMapper(mapper);
                }
            }
        };
    }

}
