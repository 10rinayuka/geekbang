package org.geekbang.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author riku
 * @Classname AspectJDemo
 * @Date 2021/3/11 0:01
 * @Description AspectJ 示例
 */
@Aspect // 声明为切面
@Configuration // Configuration Class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AspectJAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationDemo.class);

        context.refresh();

        AspectJAnnotationDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationDemo.class);

        context.close();
    }
}
