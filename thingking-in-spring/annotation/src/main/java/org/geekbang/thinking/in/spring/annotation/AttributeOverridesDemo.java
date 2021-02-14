package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 注解属性覆盖 示例
 *
 * @author jay
 * @date 2021/02/14
 */
@MyComponentScan2(packages = "org.geekbang.thinking.in.spring.annotation")
public class AttributeOverridesDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AttributeOverridesDemo.class);

        context.refresh();

        TestClass testClass = context.getBean(TestClass.class);
        System.out.println(testClass);

        context.close();
    }
}
