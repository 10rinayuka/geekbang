package org.geekbang.thinking.in.spring.ioc.dependency.injection.setter;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author riku
 * @Classname AnnotationDependencySetterInjectionDemo
 * @Date 2020/12/7 23:19
 * @Description 基于 Java 注解 Setter 方法注入示例
 */
public class AnnotationDependencySetterInjectionDemo {

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
