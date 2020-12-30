package org.geekbang.thinking.in.spring.ioc.dependency.injection.setter;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author riku
 * @Classname XmlDependencySetterInjectionDemo
 * @Date 2020/12/7 23:06
 * @Description 基于 XML 资源依赖 Setter 方法注入示例
 */
public class XmlDependencySetterInjectionDemo {

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-setter-injection.xml");

        // 创建依赖查找且创建 Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);

    }
}
