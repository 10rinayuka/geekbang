package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author riku
 * @Classname BeanDefinitionCreationDemo
 * @Date 2020/11/1 22:31
 * @Description {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1. 通过BeanDefinitionBuilder Builder模式
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder = beanDefinitionBuilder
                .addPropertyValue("age", 34)
                .addPropertyValue("name", "test");

        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanDefinition 并非最终状态，可以自定义修改

        // 2. 通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("age", 29);
//        mutablePropertyValues.addPropertyValue("name", "riku01");
        mutablePropertyValues.add("age", 29)
                .add("name", "riku01");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
