package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author riku
 * @Classname BeanConfigurationMetadataDemo
 * @Date 2021/1/13 23:44
 * @Description Bean 配置元信息 示例
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {

        // BeanDefinition 的定义/声明
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "riku");

        // 获取 BeanDefinition
        // 附加属性（不影响 Bean 实例化、属性赋值、初始化）, 可以通过 add BeanPostProcessor 进行自定义处理
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setAttribute("name", "陆");

        // 当前 BeanDefinition 来自于何方
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);

                    // 限定 来源的 自定义处理
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        // 属性（存储）上下文
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return null;
            }
        });

        // 注册 BeanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
