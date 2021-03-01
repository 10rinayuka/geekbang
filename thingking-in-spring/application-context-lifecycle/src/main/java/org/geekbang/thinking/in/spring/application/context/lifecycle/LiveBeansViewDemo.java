package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

/**
 * @author riku
 * @Classname LiveBeansViewDemo
 * @Date 2021/3/2 0:31
 * @Description {@link LiveBeansView}
 * @see LiveBeansView
 */
public class LiveBeansViewDemo {

    public static void main(String[] args) throws IOException {

        // 添加 LiveBeansView 的 ObjectName 的 domain
        System.setProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME, "org.geekbang.thinking.in.spring");

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LiveBeansViewDemo.class);

        context.refresh();

        System.out.println("按任意键继续...");
        System.in.read();

        context.close();

        /**
         * [
         *   {
         *     "context": "org.springframework.context.annotation.AnnotationConfigApplicationContext@7f63425a",
         *     "parent": null,
         *     "beans": [
         *       {
         *         "bean": "liveBeansViewDemo",
         *         "aliases": [],
         *         "scope": "singleton",
         *         "type": "org.geekbang.thinking.in.spring.application.context.lifecycle.LiveBeansViewDemo",
         *         "resource": "null",
         *         "dependencies": []
         *       }
         *     ]
         *   }
         * ]
         */
    }
}
