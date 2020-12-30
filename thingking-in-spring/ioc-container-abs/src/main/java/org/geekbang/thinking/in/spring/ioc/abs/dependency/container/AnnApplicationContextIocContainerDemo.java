package org.geekbang.thinking.in.spring.ioc.abs.dependency.container;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author riku
 * @Classname BeanFactoryIocContainerDemo
 * @Date 2020/11/1 20:45
 * @Description {@link ApplicationContext} 作为容器示例
 */
public class AnnApplicationContextIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 当前类作为配置类
        applicationContext.register(AnnApplicationContextIocContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        System.out.println(((ListableBeanFactory) applicationContext).getBeansOfType(User.class));
        System.out.println(applicationContext.getBean("riku"));
        System.out.println(applicationContext.getBean("getSuperUser"));

        System.out.println(applicationContext.getAutowireCapableBeanFactory());

        // 关闭 应用上下文
        applicationContext.close();
    }

    /**
     * 通过Java注解的方式，定义了一个Bean
     * @return
     */
    @Bean(name = {"user", "riku"})
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("riku222");
        return user;
    }

    @Bean
    public SuperUser getSuperUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(2L);
        superUser.setName("riku2");
        superUser.setAddress("jiaxing");
        return superUser;
    }
}
