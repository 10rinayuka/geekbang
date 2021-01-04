package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @author riku
 * @Classname MergedBeanDefinitionDemo
 * @Date 2021/1/4 23:51
 * @Description BeanDefinition 合并 示例
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 ClassPath 加载 XML 资源
        Resource resource = new ClassPathResource(location);
        beanDefinitionReader.loadBeanDefinitions(resource);

        System.out.println("User: " + beanFactory.getBean("user", User.class));

        System.out.println("SuperUser: " + beanFactory.getBean("superUser", User.class));
    }
}
