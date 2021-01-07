package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname BeanInstantiationLifecycleDemo
 * @Date 2021/1/6 22:51
 * @Description Bean 实例化生命周期 示例
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        System.out.println("----------executeBeanFactory--------");
        executeBeanFactory();
        System.out.println("----------executeApplicationContext--------");
        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 ClassPath 加载 XML 资源
//        Resource resource = new ClassPathResource(location);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        beanDefinitionReader.loadBeanDefinitions(locations);

        System.out.println("User: " + beanFactory.getBean("user", User.class));
        System.out.println("SuperUser: " + beanFactory.getBean("superUser", User.class));

        System.out.println("UserHolder: " + beanFactory.getBean("userHolder", UserHolder.class));

    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
//        applicationContext.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);

        applicationContext.refresh();

        System.out.println("User: " + applicationContext.getBean("user", User.class));
        System.out.println("SuperUser: " + applicationContext.getBean("superUser", User.class));

        System.out.println("UserHolder: " + applicationContext.getBean("userHolder", UserHolder.class));

        applicationContext.close();

    }
}

