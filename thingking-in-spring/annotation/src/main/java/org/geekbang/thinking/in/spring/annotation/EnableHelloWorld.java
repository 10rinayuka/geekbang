package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活 "Hello World" 注解模块
 *
 * @author jay
 * @date 2021/02/14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 1. 通过 Configuration Class 实现
//@Import(HelloWorldConfiguration.class)

// 2. 通过 ImportSelector 接口实现
//@Import(HelloWorldImportSelector.class)

// 3. 通过 ImportBeanDefinitionRegistrar 接口实现
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
