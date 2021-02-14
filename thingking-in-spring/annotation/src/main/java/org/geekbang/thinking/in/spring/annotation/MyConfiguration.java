package org.geekbang.thinking.in.spring.annotation;

import java.lang.annotation.*;

/**
 * 配置注解
 *
 * @author jay
 * @date 2021/02/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyConfiguration {

    /**
     * 名称属性
     * @return
     */
    String name();
}
