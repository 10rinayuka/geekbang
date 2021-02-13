package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 Spring 事件 示例
 *
 * @author jay
 * @date 2021/02/12
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());
        // 添加 Spring 事件监听器 - 监听所有 ApplicationEvent 事件
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("Event: " + event);
            }
        });

        context.refresh();

        // 发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello World!"));
        context.publishEvent(new MySpringEvent2("2021"));

        context.close();
    }
}
