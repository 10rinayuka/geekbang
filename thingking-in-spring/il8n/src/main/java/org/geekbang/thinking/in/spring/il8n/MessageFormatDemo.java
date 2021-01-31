package org.geekbang.thinking.in.spring.il8n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author riku
 * @Classname MessageFormatDemo
 * @Date 2021/2/1 0:54
 * @Description {@link MessageFormat} 示例
 * @see MessageFormat
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String mesagePattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";

        MessageFormat messageFormat = new MessageFormat(mesagePattern);

        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 MessageFormatPattern
        System.out.println("----重置 MessageFormatPattern----");
        mesagePattern = "This is a text : {0}";
        messageFormat.applyPattern(mesagePattern);
        result = messageFormat.format(new Object[]{"Hello World", "6666"});
        System.out.println(result);

        // 重置 Locale
        System.out.println("----重置 Locale----");
        messageFormat.setLocale(Locale.ENGLISH);
        mesagePattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(mesagePattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 Format
        // 根据参数索引 来设置
        System.out.println("----重置 Format----");
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

    }
}
