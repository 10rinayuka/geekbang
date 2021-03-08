package org.geekbang.thinking.in.spring.aop.overview.interceptor;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.DefaultEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author riku
 * @Classname AopInterceptorDemo
 * @Date 2021/3/7 21:31
 * @Description AOP 拦截器 示例
 */
public class AopInterceptorDemo {

    public static void main(String[] args) {
        // 前置模式 + 后置模式
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        EchoService echoService = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {

                    // 前置拦截器
                    final BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };
                    Long startTime = 0L;
                    Long endTime = 0L;
                    try {
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        EchoService service = new DefaultEchoService();
                        final String echo = service.echo((String) args[0]);

                        // 方法执行后置拦截器
                        AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                                return System.currentTimeMillis();
                            }
                        };
                        // 执行 after
                        endTime = (Long) afterReturnInterceptor.after(proxy, method, args, echo);
                        return echo;
                    } finally {

                        // finally 后置拦截器
                        TimeFinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Long costTime = (Long) finallyInterceptor.finalize(proxy, method, args, null);
                        System.out.println("echo 方法执行的实现: " + costTime + "ms.");
                    }
                }

                return null;
            }
        });

        final String ret = echoService.echo("Hello World(Interceptor)");
        System.out.println(ret);

    }
}

class TimeFinallyInterceptor implements FinallyInterceptor {

    Long startTime = 0L;
    Long endTime = 0L;

    public TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
        final long costTime = endTime - startTime;
        return costTime;
    }
}