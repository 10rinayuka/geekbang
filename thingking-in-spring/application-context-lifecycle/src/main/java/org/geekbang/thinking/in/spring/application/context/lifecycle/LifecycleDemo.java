package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author riku
 * @Classname LifecycleDemo
 * @Date 2021/3/3 23:28
 * @Description 自定义 {@link Lifecycle} Bean 示例
 * @see Lifecycle
 */
public class LifecycleDemo {

    public static void main(String[] args) {
        final GenericApplicationContext context = new GenericApplicationContext();

        // 注册 MyLifecycle 成为一个Spring Bean
        context.registerBeanDefinition("myLifecycle",
                BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        context.refresh();

        // 启动 Spring 应用上下文、
        context.start();

        // 停止 Spring 应用上下文
        context.stop();

        context.close();
    }
}
