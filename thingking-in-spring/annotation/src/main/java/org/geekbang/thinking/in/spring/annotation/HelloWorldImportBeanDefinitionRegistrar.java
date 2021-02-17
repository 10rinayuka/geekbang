package org.geekbang.thinking.in.spring.annotation;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld 模块 {@link ImportBeanDefinitionRegistrar}
 *
 * @author jay
 * @date 2021/02/14
 * @see ImportBeanDefinitionRegistrar
 */
public class HelloWorldImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AbstractBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(HelloWorldClass.class);
        // registry.registerBeanDefinition("helloWorld", beanDefinition);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
    }
}
