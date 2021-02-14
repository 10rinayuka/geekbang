package org.geekbang.thinking.in.spring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 Component 注解
 *
 * @author jay
 * @date 2021/02/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 元注解，实现 @Component "派生性"
public @interface MyComponent {
}
