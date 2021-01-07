package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * @author riku
 * @Classname BeanInstantiationLifecycleDemo
 * @Date 2021/1/6 22:51
 * @Description Bean 实例化生命周期 示例
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 ClassPath 加载 XML 资源
//        Resource resource = new ClassPathResource(location);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        beanDefinitionReader.loadBeanDefinitions(locations);

        System.out.println("User: " + beanFactory.getBean("user", User.class));
        System.out.println("SuperUser: " + beanFactory.getBean("superUser", User.class));

        System.out.println("UserHolder: " + beanFactory.getBean("userHolder", UserHolder.class));

    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                SuperUser superUser = new SuperUser();
                superUser.setName("自定义 SuperUser");
                return superUser; // 返回一个自定义的普通示例，覆盖
            }
            return null; // 保持不变
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                // 不允许属性赋值 - 属性赋值被跳过
                return false;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                // number 配置
                propertyValues.addPropertyValue("number", "1");

                if (propertyValues.contains("description")) {
                    propertyValues.removePropertyValue("description");
                    propertyValues.addPropertyValue("description", "the user holder v2");
                }
                return propertyValues;
            }
            return null;
        }
    }
}
