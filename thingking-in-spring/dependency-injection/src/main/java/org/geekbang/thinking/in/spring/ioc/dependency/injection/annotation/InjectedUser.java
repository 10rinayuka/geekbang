package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注解
 * @author riku
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
