package org.geekbang.thinking.in.spring.ioc.dependency.injection.aware;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author riku
 * @Classname AnnotationDependencyConstructorInjectionDemo
 * @Date 2020/12/7 23:19
 * @Description 基于 ${@link Aware} 接口回调的 依赖注入示例
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    public static final String xmlResourcePath = "classpath:/";

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        // 启动 Spring 上下文
        context.refresh();

        System.out.println(applicationContext == context);
        System.out.println(beanFactory == context.getBeanFactory());

        // 显示关闭 Spring 上下文
        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
