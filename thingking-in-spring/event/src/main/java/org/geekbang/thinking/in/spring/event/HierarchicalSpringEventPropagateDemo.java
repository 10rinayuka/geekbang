package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author riku
 * @Classname HierarchicalSpringEventPropagateDemo
 * @Date 2021/2/9 23:58
 * @Description 层次性 Spring 事件传播 示例
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 1. 创建 parent Spring 应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        // 2. 创建 current Spring 应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);

        // 3. current -> parent
        currentContext.setParent(parentContext);

        // 4. 启动 parentContext 应用上下文
        parentContext.refresh();

        // 4. 启动 currentContext 应用上下文
        currentContext.refresh();

        // 关闭所有 Spring 应用上下文
        currentContext.close();
        parentContext.close();

//        监听到 Spring 应用上下文 [ID : parent-context] 的事件 : ContextRefreshedEvent
//        监听到 Spring 应用上下文 [ID : current-context] 的事件 : ContextRefreshedEvent
//        监听到 Spring 应用上下文 [ID : current-context] 的事件 : ContextRefreshedEvent
//        监听到 Spring 应用上下文 [ID : current-context] 的事件 : ContextClosedEvent
//        监听到 Spring 应用上下文 [ID : current-context] 的事件 : ContextClosedEvent
//        监听到 Spring 应用上下文 [ID : parent-context] 的事件 : ContextClosedEvent

    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {

        // 去重复事件
        private static Set<ApplicationContextEvent> processEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {

            if (processEvents.add(event)) {
                System.out.printf("监听到 Spring 应用上下文 [ID : %s] 的事件 : %s\n",
                        event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }
}
