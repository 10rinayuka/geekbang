package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static org.geekbang.thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * @author riku
 * @Classname ValidatorDemo
 * @Date 2021/2/3 22:41
 * @Description 自定义 Spring {@link Validator} 示例
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 创建 Validator
        final UserValidator validator = new UserValidator();
        // 判断是否支持 目标对象类型
        final User user = new User();
        System.out.println("User 对象是否被 UserValidator 支持校验: " + validator.supports(user.getClass()));

        // 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        user.setName("riku");
        validator.validate(user, errors);

        // 获取 MessageResource 对象
        final MessageSource messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
            final String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            // 是否 是 User 子类或者当前类
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
            ValidationUtils.rejectIfEmpty(errors, "id", "id.required");
            String userName = user.getName();
        }
    }
}
