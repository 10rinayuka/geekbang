package org.geekbang.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname AfterReturnInterceptor
 * @Date 2021/3/7 21:39
 * @Description （方法返回）后置拦截器
 */
public interface AfterReturnInterceptor {
    /**
     * 后置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
