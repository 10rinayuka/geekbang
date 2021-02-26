package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author riku
 * @Classname EnvironmentChangeDemo
 * @Date 2021/2/25 23:48
 * @Description {@link Environment} 配置属性源变更 示例
 * @see Environment
 */
public class EnvironmentChangeDemo {

    /**
     * 不具备 动态更新 的能力
     */
    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentChangeDemo.class);

        // Spring 应用上下文启动前，调整 Environment 中的 PropertySource
        ConfigurableEnvironment environment = context.getEnvironment();
        // 获取 MutablePropertySources 对象
        MutablePropertySources propertySources = environment.getPropertySources();
        // 动态地插入 PropertySource 到 propertySources
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "乙女音");
        final MapPropertySource propertySource = new MapPropertySource("first-property-source", source);
        // 前置新的配置源
        propertySources.addFirst(propertySource);

        context.refresh();

        source.put("user.name", "古守");

        EnvironmentChangeDemo environmentChangeDemo = context.getBean(EnvironmentChangeDemo.class);
        System.out.println(environmentChangeDemo.userName);

        for (PropertySource<?> ps : propertySources) {
            System.out.printf("propertySources(name=%s) 'user.name' 属性: %s\n", ps.getName(), ps.getProperty("user.name"));
        }

        context.close();
    }
}
