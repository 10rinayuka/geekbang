package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author riku
 * @Classname InjectingEnvironmentDemo
 * @Date 2021/2/20 0:18
 * @Description 注入 {@link Environment} 示例
 */
@Configuration
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;
    @Autowired
    private Environment environment2;

    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingEnvironmentDemo.class);

        context.refresh();

        InjectingEnvironmentDemo injectingEnvironmentDemo = context.getBean(InjectingEnvironmentDemo.class);
        System.out.println(injectingEnvironmentDemo);
        System.out.println("Environment...");
        // 是单例对象
        System.out.println(injectingEnvironmentDemo.environment == injectingEnvironmentDemo.environment2);
        // 回调赋值的对象 和上下文中的 environment 是同一个对象
        System.out.println(injectingEnvironmentDemo.environment == context.getEnvironment());

        System.out.println("Application...");
        System.out.println(injectingEnvironmentDemo.applicationContext == injectingEnvironmentDemo.applicationContext2);
        System.out.println(injectingEnvironmentDemo.applicationContext == context);

        System.out.println("Environment - Application...");
        System.out.println(injectingEnvironmentDemo.environment == injectingEnvironmentDemo.applicationContext.getEnvironment());

        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
