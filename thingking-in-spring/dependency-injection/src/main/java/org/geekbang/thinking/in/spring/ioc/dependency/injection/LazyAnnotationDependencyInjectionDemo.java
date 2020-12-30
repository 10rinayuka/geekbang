package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * @author riku
 * @Classname QualifierAnnotationDependencyInjectionDemo
 * @Date 2020/12/20 23:51
 * @Description {@link ObjectProvider} 注解依赖注入
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("实时 user " + demo.user);
        System.out.println("延时 user provider " + demo.userObjectProvider.getObject());
        System.out.println("--------延时 user集合对象--------");
        demo.userObjectProvider.forEach(System.out::println);
        System.out.println("延时 user factory" + demo.userObjectFactory.getObject());

        applicationContext.close();
    }
}
