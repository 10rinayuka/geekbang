package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author riku
 * @Classname BeanInitializationLifecycleDemo
 * @Date 2021/1/6 22:51
 * @Description Bean 初始化生命周期 示例
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        System.out.println("----------executeBeanFactory--------");
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 添加 CommonAnnotationBeanPostProcessor 解决 依赖注解驱动的 @PostConstrictor 没有调用的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 ClassPath 加载 XML 资源
//        Resource resource = new ClassPathResource(location);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        beanDefinitionReader.loadBeanDefinitions(locations);

        System.out.println("User: " + beanFactory.getBean("user", User.class));
        System.out.println("SuperUser: " + beanFactory.getBean("superUser", User.class));

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        // 需要显示的执行 preInstantiateSingletons 方法，才能回调 afterSingletonsInstantiated 方法
        beanFactory.preInstantiateSingletons();

        System.out.println("UserHolder: " + userHolder);


    }

}

