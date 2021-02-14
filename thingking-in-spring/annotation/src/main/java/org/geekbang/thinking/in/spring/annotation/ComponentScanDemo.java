package org.geekbang.thinking.in.spring.annotation;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *  {@link Component} 扫描 示例
 *
 * @author jay
 * @date 2021/02/13
 * @see Component
 * @see ComponentScan
 */

// 指定 Class-Path(s)
//@ComponentScan(basePackages = "org.geekbang.thinking.in.spring.annotation")
//@MyComponentScan(scanBeanPackages = "org.geekbang.thinking.in.spring.annotation")
@MyComponentScan2(scanBeanPackages = "org.geekbang.thinking.in.spring.annotation")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ComponentScanDemo.class);

        context.refresh();

        // 依赖查找
        TestClass testClass = context.getBean(TestClass.class);
        System.out.println(testClass);

        context.close();
    }
}
