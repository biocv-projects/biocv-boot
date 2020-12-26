package com.biocv.boot.autoconfigure.logging;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 一个表示日志要保存到数据库的注解
 *
 * @author kai
 * @date 2020/12/25 16:29
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
@Documented
public @interface DbLog {
}
