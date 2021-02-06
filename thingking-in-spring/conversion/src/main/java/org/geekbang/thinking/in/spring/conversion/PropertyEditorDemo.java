package org.geekbang.thinking.in.spring.conversion;

import java.beans.PropertyEditor;
import java.util.Properties;

/**
 * @author riku
 * @Classname PropertyEditorDemo
 * @Date 2021/2/6 21:22
 * @Description {@link PropertyEditor} 示例
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        // 模拟 Spring Framework 操作

        String text = "name = 北原ゆか";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();

        // 将文本内容传递到 PropertyEditor 实现的 setAsText 方法
        propertyEditor.setAsText(text);

        // 通过 getValue 获取类型转换后的对象
        Properties value = (Properties) propertyEditor.getValue();

        System.out.println(value);
        System.out.println(propertyEditor.getAsText());
    }
}
