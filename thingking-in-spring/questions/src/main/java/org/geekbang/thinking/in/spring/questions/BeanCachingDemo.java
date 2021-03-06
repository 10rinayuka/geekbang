package org.geekbang.thinking.in.spring.questions;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author riku
 * @Classname ObjectFactoryLookupDemo
 * @Date 2021/3/5 21:56
 * @Description Bean 是否缓存 示例
 * @see ObjectFactory
 * @see ObjectProvider
 */
public class BeanCachingDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanCachingDemo.class);

        context.refresh();

        BeanCachingDemo beanCachingDemo = context.getBean(BeanCachingDemo.class);

        final User user = context.getBean(User.class);
        for (int i = 0; i < 9; i++) {

            System.out.println(user == context.getBean(User.class));
        }

        context.close();
    }

    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user() {
        final User user = new User();
        user.setId(1L);
        user.setName("ミレイ");
        return user;
    }
}
