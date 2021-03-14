package org.geekbang.thinking.in.spring.aop.features.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname EchoServiceMethodInterceptor
 * @Date 2021/3/11 21:49
 * @Description TODO
 * @see MethodInterceptor
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        System.out.println("拦截 EchoService 的方法: " + method);
        // 返回 被代拦截方法的执行结果
        return invocation.proceed();
    }
}
