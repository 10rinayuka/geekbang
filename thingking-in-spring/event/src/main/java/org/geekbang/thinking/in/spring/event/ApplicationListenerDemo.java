package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author riku
 * @Classname ApplicationListenerDemo
 * @Date 2021/2/9 23:01
 * @Description {@link ApplicationListener} 示例
 * @see ApplicationListener
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
//        final GenericApplicationContext context = new GenericApplicationContext();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        // 方法一：基于 Spring 接口，注册应用上下文事件
        // a 方法：基于 ConfigurableApplicationContext API 实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("接收到 Spring 事件: " + event);
            }
        });

        // b 方法：基于 ApplicationListener 注册为 Spring Bean
        // 通过 Configuration Class 来注册
        context.register(MyApplicationListener.class);


        // 方法二：基于 Spring 注解 - @EventListener

        // ApplicationEventMulticaster

        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello World") {
        });

        applicationEventPublisher.publishEvent("Hello World2");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("myApplicationListener - 接收到 Spring ContextRefreshedEvent");

        }
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent1) - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent2(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent2) - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        println("@EventListener（异步） - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener - 接收到 Spring ContextClosedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener - 接收到 Spring ContextStartedEvent");
    }

    private static void println(Object printable) {
        System.out.printf("[线程: %s] : %s\n", Thread.currentThread().getName(), printable);
    }
}
