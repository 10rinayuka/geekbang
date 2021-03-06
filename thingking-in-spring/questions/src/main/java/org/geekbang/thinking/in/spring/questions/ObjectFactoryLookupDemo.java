package org.geekbang.thinking.in.spring.questions;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author riku
 * @Classname ObjectFactoryLookupDemo
 * @Date 2021/3/5 21:56
 * @Description {@link ObjectFactory} 延迟依赖查找 示例
 * @see ObjectFactory
 * @see ObjectProvider
 */
public class ObjectFactoryLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectFactoryLookupDemo.class);

        context.refresh();

        ObjectFactoryLookupDemo objectFactoryLookupDemo = context.getBean(ObjectFactoryLookupDemo.class);

        final ObjectFactory<User> userObjectFactory = objectFactoryLookupDemo.userObjectFactory;
        final ObjectProvider<User> userObjectProvider = objectFactoryLookupDemo.userObjectProvider;
        // 不同对象 -> false
        System.out.println("userObjectFactory == userObjectProvider: "
                + (userObjectFactory == userObjectProvider));

        // 都是 ObjectFactory -> true
        System.out.println("userObjectFactory.class == userObjectProvider.class: "
                + (userObjectFactory.getClass() == userObjectProvider.getClass()));

        // 实际对象（延迟查找）
        System.out.println("User: " + userObjectFactory.getObject());
        System.out.println("User: " + userObjectProvider.getObject());
        System.out.println("User: " + context.getBean(User.class));

        context.close();
    }

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Bean
    @Lazy
    public static User user() {
        final User user = new User();
        user.setId(1L);
        user.setName("ミレイ");
        return user;
    }
}
