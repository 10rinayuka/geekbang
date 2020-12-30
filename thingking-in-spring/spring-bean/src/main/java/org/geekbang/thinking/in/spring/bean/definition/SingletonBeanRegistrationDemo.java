package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author riku
 * @Classname SingletonBeanRegistrationDemo
 * @Date 2020/11/17 0:35
 * @Description 单体 Bean 注册实例
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 创建外部对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册外部单例对象
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        // 依赖查找
        UserFactory userFactory1 = beanFactory.getBean("userFactory", UserFactory.class);
        // 对比
        System.out.println("外部实例: " + userFactory);
        System.out.println("注册示例: " + userFactory1);
        System.out.println(userFactory1 == userFactory);

        applicationContext.close();

    }
}
