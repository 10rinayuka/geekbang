package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.geekbang.thinking.in.spring.aop.features.aspect.AspectConfiguration2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Random;

/**
 * @author riku
 * @Classname AspectJPointcutDemo
 * @Date 2021/3/12 0:42
 * @Description Pointcut 示例
 */
@EnableAspectJAutoProxy
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class,
                AspectConfiguration.class,
                AspectConfiguration2.class);
        context.refresh();

        final AspectJAnnotatedPointcutDemo aspectJPointcutDemo = context.getBean(AspectJAnnotatedPointcutDemo.class);

        aspectJPointcutDemo.execute();

        context.close();
    }

    public void execute() {
//        final Random random = new Random();
//        if (random.nextBoolean()) {
//            throw new RuntimeException("For Purpose");
//        }
        System.out.println("execute()");
    }
}
