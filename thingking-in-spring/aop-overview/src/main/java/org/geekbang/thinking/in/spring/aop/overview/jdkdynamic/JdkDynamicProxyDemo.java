package org.geekbang.thinking.in.spring.aop.overview.jdkdynamic;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.DefaultEchoService;
import org.geekbang.thinking.in.spring.aop.overview.staticproxy.ProxyEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author riku
 * @Classname JdkDynamicProxyDemo
 * @Date 2021/3/6 16:40
 * @Description JDK 动态代理 示例
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        EchoService echoService = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    final ProxyEchoService service = new ProxyEchoService(new DefaultEchoService());
                    return service.echo((String) args[0]);
                }
                return null;
            }
        });

        System.out.println(echoService.echo("Hello World! (jdk)"));

        //
        final Object proxy2 = Proxy.newProxyInstance(classLoader, new Class[]{Comparable.class}, (proxy, method, args1) -> {
            return null;
        });

        System.out.println(proxy2);
    }
}
