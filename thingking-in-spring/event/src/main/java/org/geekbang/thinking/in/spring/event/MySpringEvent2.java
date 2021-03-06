package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author jay
 * @date 2021/02/12
 */
public class MySpringEvent2 extends MySpringEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message 事件消息
     */
    public MySpringEvent2(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    @Override
    public String getMessage() {
        return getSource();
    }
}
