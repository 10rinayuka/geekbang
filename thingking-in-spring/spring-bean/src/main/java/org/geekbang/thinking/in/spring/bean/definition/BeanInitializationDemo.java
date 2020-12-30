package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author riku
 * @Classname BeanInitializationDemo
 * @Date 2020/11/9 0:45
 * @Description Bean 初始化 Demo
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);

        // 启动引用上下文
        applicationContext.refresh();
        System.out.println("Spring 上下文应用已启动...");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        System.out.println("Spring 上下文应用准备关闭...");
        // 关闭 应用上下文
        applicationContext.close();
        System.out.println("Spring 上下文应用已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
