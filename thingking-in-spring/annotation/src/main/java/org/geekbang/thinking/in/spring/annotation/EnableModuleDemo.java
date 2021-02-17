package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable 模块驱动 示例
 *
 * @author jay
 * @date 2021/02/14
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EnableModuleDemo.class);

        context.refresh();

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        context.close();
    }
}
