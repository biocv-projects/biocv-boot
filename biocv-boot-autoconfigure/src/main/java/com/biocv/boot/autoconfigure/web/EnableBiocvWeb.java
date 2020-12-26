package com.biocv.boot.autoconfigure.web;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author kai
 * @date 2020/12/23 01:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(WebAutoConfiguration.class)
public @interface EnableBiocvWeb {



}
