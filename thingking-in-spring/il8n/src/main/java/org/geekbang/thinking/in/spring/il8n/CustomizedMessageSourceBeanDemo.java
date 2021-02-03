package org.geekbang.thinking.in.spring.il8n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author riku
 * @Classname CustomizedMessageSourceBeanDemo
 * @Date 2021/2/3 0:38
 * @Description Spring Boot 场景下自定义 {@link MessageSource} Bean
 * @see MessageSource
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {

    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CustomizedMessageSourceBeanDemo.class, args);

        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            // 查找 MessageSource 的 BeanDefinition
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));

            // 查找 MessageSource Bean
            MessageSource messageSource = context.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }



        context.close();
    }
}
