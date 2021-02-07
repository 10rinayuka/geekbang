package org.geekbang.thinking.in.spring.conversion;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * @author riku
 * @Classname SpringCustomizedPropertyEditorDemo
 * @Date 2021/2/6 22:05
 * @Description Spring 自定义 {@link PropertyEditor}
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        applicationContext.refresh();

        final User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
