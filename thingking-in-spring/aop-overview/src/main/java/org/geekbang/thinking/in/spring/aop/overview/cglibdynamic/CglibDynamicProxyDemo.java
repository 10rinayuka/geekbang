package org.geekbang.thinking.in.spring.aop.overview.cglibdynamic;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.DefaultEchoService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname CglibDynamicProxyDemo
 * @Date 2021/3/9 21:53
 * @Description CGLIB 动态代理 示例
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {

        final Enhancer enhancer = new Enhancer();
        // 指定 super class = DefaultEchoService
        Class<?> superClass = DefaultEchoService.class;
        enhancer.setSuperclass(superClass);
        // 指定拦截接口
        enhancer.setInterfaces(new Class[]{EchoService.class});

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {

                final long startTime = System.currentTimeMillis();
                // source -> CGLIB Class
                // 目标类 -> DefaultEchoService
                // final Object result = method.invoke(source, args); 错误使用
                Object result = methodProxy.invokeSuper(source, args);
                final long costTime = System.currentTimeMillis() - startTime;

                System.out.println("[CGLIB]echo 方法执行的实现: " + costTime + "ms.");
                return result;
            }
        });

        // 创建代理对象
        final EchoService echoService = (EchoService) enhancer.create();
        System.out.println(echoService.echo("hello world"));
    }
}
