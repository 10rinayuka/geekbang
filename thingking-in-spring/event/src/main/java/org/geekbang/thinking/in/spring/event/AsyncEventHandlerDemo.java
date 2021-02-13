package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理器 示例
 *
 * @author jay
 * @date 2021/02/12
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());

        // 初始化 ApplicationEventMulticaster
        context.refresh();

        // 依赖查找
        ApplicationEventMulticaster multicaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

        // 判断当前 ApplicationEventMulticaster 是不是 SimpleApplicationEventMulticaster
        if (multicaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) multicaster;
            // 切换 taskExecutor
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor(
                    new CustomizableThreadFactory("my-spring-event-thread-pool"));
            // 同步 -> 异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

            // 添加 ContextClosedEvent 事件处理
            multicaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!taskExecutor.isShutdown()) {
                        System.out.printf("[线程 : %s] 关闭...\n", Thread.currentThread().getName());
                        System.out.println(event.getSource());
                        taskExecutor.shutdown();
                    }
                }
            });

            simpleApplicationEventMulticaster.setErrorHandler(e ->{
                System.err.println("当 Spring 事件异常时，原因: " + e.getMessage());
            });
        }

        context.addApplicationListener(new ApplicationListener<MySpringEvent>(){
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        });

        // 发布 自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello World"));

        context.close();
    }
}
