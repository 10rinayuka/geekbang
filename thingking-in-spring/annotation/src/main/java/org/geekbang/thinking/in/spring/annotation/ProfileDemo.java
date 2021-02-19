package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * @author riku
 * @Classname ProfileDemo
 * @Date 2021/2/18 22:49
 * @Description {@link Profile}
 * @see Profile
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ProfileDemo.class);
        // 获取 可配置的 Environment 对象
        final ConfigurableEnvironment environment = context.getEnvironment();

        // 1. 设置默认 Profile 为 odd
        environment.setDefaultProfiles("odd");

        // 2. 增加活跃 Profiles
//        environment.addActiveProfile("even");

        // --spring.profiles.active = even
        // -Dspring.profiles.active=even

        context.refresh();

        final Integer number = context.getBean("number", Integer.class);
        System.out.println(number);


        context.close();
    }

    @Bean(name = "number")
    @Profile("odd") // 奇数
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even") // 偶数
    @Conditional(EvenProfileConditional.class)
    public Integer even() {
        return 2;
    }
}
