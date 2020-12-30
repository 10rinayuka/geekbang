package org.geekbang.thinking.in.spring.ioc.abs.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author riku
 * @Classname DependencyLookupDemo
 * @Date 2020/10/31 0:25
 * @Description 依赖查找Demo
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 创建上下文
        // 1. 配置文件
        // 2. 启动Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        System.out.println("实时/名称 查找");
        lookupRealTime(beanFactory);

        System.out.println("延时/名称 查找");
        lookupInLzay(beanFactory);

        System.out.println("单个Bean 类型 查找");
        lookupByType(beanFactory);

        System.out.println("集合 类型 查找");
        lookupCollectionByType(beanFactory);

        System.out.println("通过Java注解进行查找");
        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("集合对象: " + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合对象: " + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    /**
     * 实时查找
     *
     * @param beanFactory
     */
    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }

    /**
     * 延时查找
     *
     * @param beanFactory
     */
    private static void lookupInLzay(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println(user);
    }
}
