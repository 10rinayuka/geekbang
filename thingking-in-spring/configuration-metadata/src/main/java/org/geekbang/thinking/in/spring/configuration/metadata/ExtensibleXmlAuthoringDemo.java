package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author riku
 * @Classname ExtensibleXmlAuthoringDemo
 * @Date 2021/1/20 0:42
 * @Description Spring XML 元素扩展 示例
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/users-context.xml");

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
