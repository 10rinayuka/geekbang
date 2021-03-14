package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname AspectJPointcutDemo
 * @Date 2021/3/12 0:42
 * @Description 基于 XML 配置 Pointcut 示例
 */
@Configuration
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");
        context.refresh();

        final EchoService echoService = context.getBean("echoService", EchoService.class);
        System.out.println(echoService.echo("hello world!!"));

        context.close();
    }

    public void execute() {
        System.out.println("xml execute()");
    }
}
