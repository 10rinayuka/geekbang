package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author riku
 * @Classname AnnotationBeanDefinitionDemo
 * @Date 2020/11/3 0:52
 * @Description 注解方式 注册BeanDefinition
 */
// @Import方式
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class: 必须注册
        // applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 1. API 命名方式方式 注册
        registerBeanDefinition(applicationContext, "c-lujy", User.class);
        // 2. API 非命名方式 注册
        registerBeanDefinition(applicationContext, User.class);

        // 启动容器
        applicationContext.refresh();
        // 1. 通过 @Bean 的方式定义
        // 2. 通过 @Component 方式
        // 3. 通过 @Import 方式

        // 不会重复注册
//        System.out.println(applicationContext.getBeansOfType(AnnotationBeanDefinitionDemo.class));
        System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(User.class));
//        System.out.println(applicationContext.getBeansOfType(SuperUser.class));

        // 显示地关闭
        applicationContext.close();
    }

    /**
     * 命名方式注册
     *
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L)
                .addPropertyValue("name", "riku001");

        if (StringUtils.hasText(beanName)) {
            // 命名方式注册
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }


    // 通过 @Component 方式
    @Component
    public static class Config {
        @Bean
        public User getUser() {
            User user = new User();
            user.setId(1L);
            user.setName("riku_annotations");
            return user;
        }
    }

    @Bean
    Config config() {
        return new Config();
    }


//    @Bean
//    public SuperUser getSuperUser() {
//        SuperUser superUser = new SuperUser();
//        superUser.setId(2L);
//        superUser.setName("riku2");
//        superUser.setAddress("jiaxing");
//        return superUser;
//    }

}
