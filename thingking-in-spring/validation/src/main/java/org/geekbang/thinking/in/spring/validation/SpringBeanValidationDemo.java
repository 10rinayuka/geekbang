package org.geekbang.thinking.in.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author riku
 * @Classname SpringBeanValidationDemo
 * @Date 2021/2/3 23:02
 * @Description Spring Bean Validation 整合示例
 * @see Validator
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

        final UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        final User user = new User();
//        user.setName("riku");
        userProcessor.process(user);

        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void process(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User {

        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
