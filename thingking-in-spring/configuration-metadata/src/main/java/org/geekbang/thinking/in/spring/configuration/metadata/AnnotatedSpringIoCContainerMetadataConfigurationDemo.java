package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @author riku
 * @Classname AnnotatedSpringIoCContainerMetadataConfigurationDemo
 * @Date 2021/1/19 23:44
 * @Description 基于 Java 注解 Spring IoC 容器元信息配置 示例
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    @Bean
    public User configuredUser(@Value("${myUser.id}") Long id, @Value("${myUser.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        // 启动 应用上下文
        applicationContext.refresh();

        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println("User Bean name : " + entry.getKey() + ", content : " + entry.getValue());
        }

        applicationContext.close();
    }
}
