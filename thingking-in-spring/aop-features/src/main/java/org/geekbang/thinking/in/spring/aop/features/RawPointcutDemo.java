package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.geekbang.thinking.in.spring.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import org.geekbang.thinking.in.spring.aop.features.pointcut.EchoServicePointcut;
import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.DefaultEchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author riku
 * @Classname RawPointcutDemo
 * @Date 2021/3/13 23:37
 * @Description API 实现 Pointcut
 */
public class RawPointcutDemo {

    public static void main(String[] args) {

//        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);
        EchoServiceEchoMethodPointcut pointcut = EchoServiceEchoMethodPointcut.INSTANCE;
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        ComposablePointcut composablePointcut = new ComposablePointcut(pointcut);
        composablePointcut.intersection(echoServicePointcut.getClassFilter());
        composablePointcut.intersection(echoServicePointcut.getMethodMatcher());


        // 将 pointcut 适配成 Advisor，不能直接使用
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(composablePointcut, new EchoServiceMethodInterceptor());


        ProxyFactory proxyFactory = new ProxyFactory(new DefaultEchoService());
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);

        final EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello World(API)"));
    }
}
