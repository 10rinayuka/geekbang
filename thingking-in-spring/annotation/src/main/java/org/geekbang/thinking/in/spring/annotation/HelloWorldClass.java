package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld Bean Class
 *
 * @author jay
 * @date 2021/02/14
 */
public class HelloWorldClass {
    @Bean
    public String helloWorld()  {
        return "Hello World - Class";
    }
}
