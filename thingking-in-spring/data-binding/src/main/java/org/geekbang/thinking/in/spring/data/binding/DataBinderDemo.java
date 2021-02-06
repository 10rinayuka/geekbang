package org.geekbang.thinking.in.spring.data.binding;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author riku
 * @Classname DataBinderDemo
 * @Date 2021/2/4 22:45
 * @Description {@link DataBinder} 示例
 */
public class DataBinderDemo {

    public static void main(String[] args) {

        // 创建空白对象
        User user = new User();
        // 1. 创建 DataBinder
        DataBinder binder = new DataBinder(user);

        // 2. 创建 PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "北原ゆか");

        // a. PropertyValues 中存在 User 没有的属性 - 忽略未知的属性
        source.put("age", 22);
        // b. PropertyValues 中存在一个嵌套属性 - 支持嵌套属性的绑定
        source.put("company.name", "生财");

        PropertyValues propertyValues = new MutablePropertyValues(source);

        // i. 调整 ignoreUnknownFields -> false
        // Invalid property 'age' of bean class [xxx.User]: Bean property 'age' is not writable or has an invalid setter method. Did you mean 'name'?
        // binder.setIgnoreUnknownFields(false);

        // ii. 调整 autoGrowNestedPaths -> false
        // Invalid property 'company' of bean class [xxx.User]: Value of nested property 'company' is null
        binder.setAutoGrowNestedPaths(false);
        // ii. 调整 ignoreInvalidFields -> true（默认调整不变化）
        // 调整 autoGrowNestedPaths -> false 不报错 company 被忽略
        binder.setIgnoreInvalidFields(true);

        // iii. 必须绑定字段
        binder.setRequiredFields("id", "name", "city");

        binder.bind(propertyValues);
        // 3. 输出 User 内容
        System.out.println(user);

        // 4. 获取绑定结果（包含错误文案、但不会报错）
        final BindingResult result = binder.getBindingResult();
        System.out.println(result);

    }
}
