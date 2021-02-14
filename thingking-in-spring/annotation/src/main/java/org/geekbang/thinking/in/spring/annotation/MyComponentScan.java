package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 {@link Component} Scan
 *
 * @author jay
 * @date 2021/02/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

    // 隐性别名
//    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    @AliasFor(annotation = ComponentScan.class, attribute = "value")
    String[] scanBeanPackages() default {"#"};
}
