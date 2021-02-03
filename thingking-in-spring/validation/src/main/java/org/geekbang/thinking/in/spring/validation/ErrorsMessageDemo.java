package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * @author riku
 * @Classname ErrorsMessageDemo
 * @Date 2021/2/3 22:18
 * @Description 错误文案 示例
 * @see Errors
 */
public class ErrorsMessageDemo {

    public static void main(String[] args) {

        // 创建 User 对象
        User user = new User();
        user.setName("rilku");

        // 选择 Errors 实现 - BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 调用 reject 或 rejectValue 方法
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");

        // 获取 Errors 中的 ObjectError或 FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        // 通过 ObjectError或 FieldError 中的 code 和 args 来关联 MessageSource 实现
        MessageSource messageSource = createMessageSource();

        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null.");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null.");

        return messageSource;
    }
}
