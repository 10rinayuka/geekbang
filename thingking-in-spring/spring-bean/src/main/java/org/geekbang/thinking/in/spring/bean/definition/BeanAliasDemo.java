package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname BeanAliasDemo
 * @Date 2020/11/2 1:17
 * @Description Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {

        // 创建上下文
        // 1. 配置文件
        // 2. 启动Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        User user = beanFactory.getBean("user", User.class);
        User rikuUser = beanFactory.getBean("riku-user", User.class);

        System.out.println(user == rikuUser);
        System.out.println(rikuUser);
    }
}
