package org.geekbang.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author riku
 * @Classname YamlPropertySourceDemo
 * @Date 2021/1/24 22:33
 * @Description 基于 XML 的 YAML 外部化配置 示例
 */
public class XmlBasedYamlPropertySourceDemo {
    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:META-INF/yaml-property-source-context.xml");

        Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);

        System.out.println(yamlMap);

    }
}
