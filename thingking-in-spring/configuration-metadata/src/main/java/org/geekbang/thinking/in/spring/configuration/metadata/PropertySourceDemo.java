package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author riku
 * @Classname PropertySourceDemo
 * @Date 2021/1/21 0:57
 * @Description 外部化配置 示例
 */
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${myUser.id}") Long id, @Value("${myUser.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 扩展 Environment 中的 PropertySource
        // 必须在 refresh 方法之前 完成 扩展操作
        Map<String, Object> propertiesSource = new HashMap<>();
        // 优先级高，覆盖 .properties 文件中的配置
        propertiesSource.put("myUser.name", "riku1111");
        MapPropertySource propertySource = new MapPropertySource("first-property-source", propertiesSource);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);

        // 注册当前类作为 Configuration Class
        applicationContext.register(PropertySourceDemo.class);
        // 启动 应用上下文
        applicationContext.refresh();

        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println("User Bean name : " + entry.getKey() + ", content : " + entry.getValue());
        }

        System.out.println("PropertySources: " + applicationContext.getEnvironment().getPropertySources());

        applicationContext.close();
    }
}
