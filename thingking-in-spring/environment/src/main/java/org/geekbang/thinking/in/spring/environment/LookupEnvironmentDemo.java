package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author riku
 * @Classname LookupEnvironmentDemo
 * @Date 2021/2/20 0:18
 * @Description 依赖查找 {@link Environment} 示例
 */
@Configuration
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LookupEnvironmentDemo.class);

        context.refresh();

        LookupEnvironmentDemo lookupEnvironmentDemo = context.getBean(LookupEnvironmentDemo.class);

        // 1. 通过 ENVIRONMENT_BEAN_NAME 依赖查找
        final Environment environment = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        // 依赖查找 与 依赖注入 的对象 是同一个
        System.out.println(lookupEnvironmentDemo.environment == environment);

        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
