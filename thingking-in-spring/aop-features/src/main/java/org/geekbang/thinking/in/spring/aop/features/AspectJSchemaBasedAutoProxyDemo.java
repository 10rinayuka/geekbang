package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author riku
 * @Classname AspectJSchemaBasedAutoProxyDemo
 * @Date 2021/3/15 23:30
 * @Description 基于 XML 配置自动代理 示例
 */
public class AspectJSchemaBasedAutoProxyDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-auto-proxy-context.xml");

        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);

        System.out.println(echoService.echo("xml auto proxy."));

        context.close();
    }
}
