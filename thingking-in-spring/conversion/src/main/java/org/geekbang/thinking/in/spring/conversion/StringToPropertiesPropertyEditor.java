package org.geekbang.thinking.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * @author riku
 * @Classname StringToPropertiesPropertyEditor
 * @Date 2021/2/6 21:23
 * @Description String -> Properties {@link PropertyEditor}
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    /**
     * 实现 setAsText 方法
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (!text.contains("=")) {
            return;
        }

        // String -> Properties 转换实现
        Properties properties = new Properties();

        System.out.println("StringToPropertiesPropertyEditor 转换..." + text);

        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // 临时存储 Properties 对象
        setValue(properties);

        // next 临时获取 Properties 对象
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();

        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            textBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }

        return textBuilder.toString();
    }
}
