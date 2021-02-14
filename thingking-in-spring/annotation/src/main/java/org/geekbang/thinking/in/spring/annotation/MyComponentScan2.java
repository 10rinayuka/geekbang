package org.geekbang.thinking.in.spring.annotation;

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
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBeanPackages")
    String[] basePackages() default {};
    // @MyComponentScan2 .basePackages
    // -> @MyComponentScan .scanBeanPackages
    // -> @ComponentScan .basePackages
    // -> @ComponentScan .value

    /**
     * 与元注解 @MyComponentScan 同名属性（隐性覆盖）
     *
     * @return
     */
    String[] scanBeanPackages() default {};

    /**
     * 注解属性显性覆盖
     *
     * @return
     */
    @AliasFor("scanBeanPackages")
    String[] packages() default {};

}
