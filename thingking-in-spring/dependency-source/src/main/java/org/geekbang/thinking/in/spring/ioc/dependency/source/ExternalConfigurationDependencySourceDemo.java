package org.geekbang.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author riku
 * @Classname ExternalConfigurationDependencySourceDemo
 * @Date 2020/12/26 22:41
 * @Description 外部化配置作为依赖
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "GBK")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id: " + demo.id);
        System.out.println("demo.name: " + demo.name);
        System.out.println("demo.resource: " + demo.resource);

        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }
}
