package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * 自定义 Spring 事件监听器
 *
 * @author jay
 * @date 2021/02/12
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程 : %s] 监听事件 %s\n", Thread.currentThread().getName(), event);
    }
}
