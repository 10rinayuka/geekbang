package org.geekbang.thinking.in.spring.environment;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname PropertyPlaceholderConfigurerDemo
 * @Date 2021/2/19 23:29
 * @Description {@link PropertyPlaceholderConfigurer} 处理属性占位符 示例
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/placeholder-resolver.xml");

        User user = context.getBean("user", User.class);
        System.out.println(user);

        context.close();
    }
}
