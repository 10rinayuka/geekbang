package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @author riku
 * @Classname JavaBeansDemo
 * @Date 2021/2/6 15:55
 * @Description JavaBeans 示例
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {

        // stopClass 排除类
        final BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 属性描述符 PropertyDescriptor

        System.out.println("PropertyDescriptor");
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
//                    propertyDescriptor.getReadMethod();
//                    propertyDescriptor.getWriteMethod();
                    System.out.println(propertyDescriptor);
                });

        System.out.println("MethodDescriptors");
        Stream.of(beanInfo.getMethodDescriptors())
                .forEach(System.out::println);
    }
}
