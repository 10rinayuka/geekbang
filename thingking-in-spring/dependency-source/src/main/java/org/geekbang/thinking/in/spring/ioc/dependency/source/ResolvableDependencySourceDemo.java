package org.geekbang.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author riku
 * @Classname ResolvableDependencySourceDemo
 * @Date 2020/12/26 21:58
 * @Description ResolvableDependency 作为依赖
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // refresh -> invokeBeanFactoryPostProcessors -> 回调注册的后置处理器（beanFactory）
            beanFactory.registerResolvableDependency(String.class, "hello, world");
        });

        // 启动应用上下文
        applicationContext.refresh();

        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }
}
