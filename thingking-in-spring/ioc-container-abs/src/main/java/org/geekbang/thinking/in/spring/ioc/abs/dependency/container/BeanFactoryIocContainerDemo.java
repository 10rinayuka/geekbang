package org.geekbang.thinking.in.spring.ioc.abs.dependency.container;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author riku
 * @Classname BeanFactoryIocContainerDemo
 * @Date 2020/11/1 20:45
 * @Description {@link BeanFactory} 作为容器示例
 */
public class BeanFactoryIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载资源
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beansCount = reader.loadBeanDefinitions(location);
        System.out.println("definition beans: " + beansCount);

        System.out.println(((ListableBeanFactory) beanFactory).getBeansOfType(User.class));
    }
}
