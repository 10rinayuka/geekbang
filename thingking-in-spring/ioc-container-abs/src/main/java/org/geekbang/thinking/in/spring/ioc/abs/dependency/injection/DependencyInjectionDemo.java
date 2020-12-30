package org.geekbang.thinking.in.spring.ioc.abs.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author riku
 * @Classname DependencyInjectionDemo
 * @Date 2020/11/1 13:53
 * @Description 依赖注入Demo
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

        // 2. 启动Spring上下文
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        System.out.println("自动注入");
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory() == beanFactory.getAutowireCapableBeanFactory());
        System.out.println("user beanFactory(内建依赖): " + userRepository.getBeanFactory());
        System.out.println("beanFactory: " + beanFactory);
        // System.out.println(beanFactory.getBean(BeanFactory.class)); // 异常

        System.out.println("延时注入");
        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        System.out.println(" ApplicationContext bjectFactory<ApplicationContext> ClassPathXmlApplicationContext");
        ObjectFactory<ApplicationContext> appObjectFactory = userRepository.getAppObjectFactory();
        System.out.println(appObjectFactory.getObject());
        System.out.println(appObjectFactory.getObject() == beanFactory);
        System.out.println("objectFactory context: " + appObjectFactory.getObject());
        System.out.println("context: " + userRepository.getContext());
        System.out.println("beanFactory: " + beanFactory);


        // 容器内建Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取Environment Bean: " + environment);

    }
}
