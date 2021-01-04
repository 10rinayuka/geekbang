package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 元信息配置 示例
 *
 * @author jay
 * @date 2021/01/01
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于 properties 资源 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "/META-INF/user.properties";
        // 加载资源
        // 基于ClassPath 加载 properties 资源
        Resource resource = new ClassPathResource(location);
        // 指定字符集 utf-8
        EncodedResource encodedResource  = new EncodedResource(resource, "GBK");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已经加载 BeanDefinition 数量: " + beanNumbers);

        // 通过Bean Id 和类型 机型依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println("User: " + user);


    }
}
