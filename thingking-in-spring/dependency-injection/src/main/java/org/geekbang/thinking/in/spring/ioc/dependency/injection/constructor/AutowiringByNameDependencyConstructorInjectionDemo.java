package org.geekbang.thinking.in.spring.ioc.dependency.injection.constructor;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author riku
 * @Classname AutowiringByNameDependencyConstructorInjectionDemo
 * @Date 2020/12/7 23:37
 * @Description Autowiring 依赖 Constructor 方法注入示例
 */
public class AutowiringByNameDependencyConstructorInjectionDemo {
    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/autowiring-dependency-constructor-injection.xml");

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
