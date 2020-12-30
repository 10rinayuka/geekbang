package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname BeanInstantinationDemo
 * @Date 2020/11/6 23:44
 * @Description Bean 实例化示例
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        // 通过静态方法构建
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(user);

        // 实例方法方式实例化
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);

        // FactoryBean 实例化
        User userFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userFactoryBean);
    }
}
