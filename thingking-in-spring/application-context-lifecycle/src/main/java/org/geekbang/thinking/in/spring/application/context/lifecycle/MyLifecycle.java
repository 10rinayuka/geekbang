package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * @author riku
 * @Classname MyLifecycle
 * @Date 2021/3/3 23:26
 * @Description 自定义 {@link Lifecycle}
 * @see Lifecycle
 */
public class MyLifecycle implements Lifecycle {

    private boolean running = false;

    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle 启动...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle 停止...");

    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
