package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author riku
 * @Classname ValueAnnotationDemo
 * @Date 2021/2/21 23:22
 * @Description {@link Value @Value} 注解 示例
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ValueAnnotationDemo.class);

        context.refresh();

        ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);
        System.out.println(valueAnnotationDemo.userName);

        context.close();
    }
}
