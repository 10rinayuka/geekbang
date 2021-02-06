package org.geekbang.thinking.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Properties;

/**
 * @author riku
 * @Classname CustomizedPropertyEditorRegistrar
 * @Date 2021/2/6 21:58
 * @Description 自定义 {@link PropertyEditorRegistrar} 实现
 * @see PropertyEditorRegistrar
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1. 通用类型转换
        // 2. Java Bean 属性类型转换
        registry.registerCustomEditor(Properties.class, "context", new StringToPropertiesPropertyEditor());
    }
}
