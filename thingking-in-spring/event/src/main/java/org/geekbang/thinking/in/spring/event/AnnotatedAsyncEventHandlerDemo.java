package org.geekbang.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理器 示例
 *
 * @author jay
 * @date 2021/02/12
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedAsyncEventHandlerDemo.class);

        // 初始化 ApplicationEventMulticaster
        context.refresh();

        // 发布 自定义 Spring 事件
        context.publishEvent(new MySpringEvent("@Hello World"));

        context.close();
    }

    @EventListener
    @Async // 同步 -> 异步
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程 : %s] onEvent 方法监听事件 %s\n", Thread.currentThread().getName(), event);

    }

    @Bean
    public Executor taskExecutor() {
        ExecutorService taskExecutor = Executors.newSingleThreadExecutor(
                new CustomizableThreadFactory("my-spring-event-thread-pool-a"));

        return taskExecutor;
    }
}
