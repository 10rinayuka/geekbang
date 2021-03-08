package org.geekbang.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname FinallyInterceptor
 * @Date 2021/3/7 21:39
 * @Description 最终执行后置拦截器
 */
public interface FinallyInterceptor {
    /**
     * 后置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
