package org.geekbang.thinking.in.spring.annotation;

import java.lang.annotation.*;

/**
 * 自定义应用注解
 *
 * @author jay
 * @date 2021/02/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent2
@MyConfiguration(name = "my-application")
public @interface MyApplication {
}
