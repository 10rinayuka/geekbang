package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.DefaultEchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author riku
 * @Classname ProxyFactoryDemo
 * @Date 2021/3/11 21:57
 * @Description TODO
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();

        // 注入目标对象（被代理对象）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
//        proxyFactory.setTargetClass(DefaultEchoService.class);

        // 添加 Advice 实现 MethodInterceptor <- Interceptor <- Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        final EchoService echoService = (EchoService) proxyFactory.getProxy();

        System.out.println(echoService.echo("hello world!"));

    }
}
