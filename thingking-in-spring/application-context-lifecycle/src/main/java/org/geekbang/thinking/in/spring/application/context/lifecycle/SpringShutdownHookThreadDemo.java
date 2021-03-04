package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @author riku
 * @Classname LifecycleDemo
 * @Date 2021/3/3 23:28
 * @Description Spring Shutdown Hook 线程 示例
 * @see Lifecycle
 */
public class SpringShutdownHookThreadDemo {

    public static void main(String[] args) throws IOException {
        final GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("[线程 %s] ContextClosedEvent 处理\n", Thread.currentThread().getName());
            }
        });

        context.refresh();

        // 注册 Shutdown Hook
        context.registerShutdownHook();

        //
        System.out.println("按任意键继续并且关闭 Spring 应用上下文");
        System.in.read();

        context.close();
        System.out.println("同步 关闭");
    }
}
