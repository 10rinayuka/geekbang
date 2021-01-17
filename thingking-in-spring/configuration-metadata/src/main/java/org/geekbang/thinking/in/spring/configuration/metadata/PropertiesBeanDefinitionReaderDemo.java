package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author riku
 * @Classname PropertiesBeanDefinitionReaderDemo
 * @Date 2021/1/18 0:00
 * @Description {@link PropertiesBeanDefinitionReader} 示例
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建面向 Properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        // 资源加载 默认 通过 ISO-8859-1(ASCII), 实际存储 UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/META-INF/user-bean-definitions.properties");
        // 转换 带有字符编码的 EncodedResource
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("已加载" + beanDefinitionCount + "个 BeanDefinitions");

        // System.out.println(beanFactory.getBean("myUser", User.class)); // myUser 不支持
        System.out.println(beanFactory.getBean("myUser", User.class));
        System.out.println(beanFactory.getBean(User.class));

    }
}
