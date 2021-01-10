package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @author riku
 * @Classname BeanLifecycleDemo
 * @Date 2021/1/10 22:40
 * @Description TODO
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 添加 MyDestructionAwareBeanPostProcessor 执行销毁前 回调
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        // 添加 CommonAnnotationBeanPostProcessor 解决 依赖注解驱动的 @PostConstrictor 没有调用的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        beanDefinitionReader.loadBeanDefinitions(locations);

        beanFactory.preInstantiateSingletons();

        System.out.println("User: " + beanFactory.getBean("user", User.class));
        System.out.println("SuperUser: " + beanFactory.getBean("superUser", User.class));

//        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        // 需要显示的执行 preInstantiateSingletons 方法，才能回调 afterSingletonsInstantiated 方法

        System.out.println("UserHolder: " + beanFactory.getBean("userHolder", UserHolder.class));

        // destroy
        // Bean 被销毁 并不意味着被 GC
        beanFactory.destroyBean("userHolder", beanFactory.getBean("userHolder", UserHolder.class));
        System.out.println(beanFactory.getBean("userHolder", UserHolder.class));


        // GC 示例
        testGC(beanFactory);
    }

    private static void testGC(DefaultListableBeanFactory beanFactory) throws InterruptedException {
        // 彻底销毁 BeanFactory 中的单例 Bean
        beanFactory.destroySingletons();
        System.gc();

        // 等待一段时间
        Thread.sleep(1000L);

        System.gc();
    }
}
