package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.omg.DynamicAny.DynSequence;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author riku
 * @Classname SpecialBeanInstantiationDemo
 * @Date 2020/11/6 23:44
 * @Description Bean 特殊实例化示例
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        // serviceLoader 实例化 Bean
        demoServiceLoader();

        ServiceLoader serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);


        // AutowireCapableBeanFactory#createBean 实例化 Bean
        // UserFactory userFactory = beanFactory.createBean(UserFactory.class);
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.getUserInstance());

    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.getUserInstance());
        }
    }

    /**
     * 会去重
     */
    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }
}
