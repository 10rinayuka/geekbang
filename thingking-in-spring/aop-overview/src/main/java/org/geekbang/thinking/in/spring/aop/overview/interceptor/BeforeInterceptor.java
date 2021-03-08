package org.geekbang.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname BeforeInterceptor
 * @Date 2021/3/7 21:39
 * @Description 前置拦截器
 */
public interface BeforeInterceptor {
    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
