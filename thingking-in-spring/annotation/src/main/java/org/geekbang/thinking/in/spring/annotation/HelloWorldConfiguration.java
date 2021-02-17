package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld Configuration Class
 *
 * @author jay
 * @date 2021/02/14
 */
//@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld()  {
        return "Hello World - Configuration";
    }
}
