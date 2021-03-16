package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author riku
 * @Classname AspectJAnnotationAPIDemo
 * @Date 2021/3/11 0:22
 * @Description MethodBeforeAdvice 实现 before 拦截
 * @see MethodBeforeAdvice
 * @see AspectJProxyFactory
 */
public class AspectJAnnotationUsingAPIDemo {

    public static void main(String[] args) {

        // 通过创建一个 HashMap 缓存
        Map<String, Object> cache = new HashMap<>(2);

        // 创建 Proxy 工厂
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 增加 Aspect 配置类
        proxyFactory.addAspect(AspectConfiguration.class);

        // 追加 BeforeAdvice
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("当前存放是 key: %s, Value: %s \n", args[0], args[1]);
                }
            }
        });

        // 追加 AfterReturning Advice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("当前存放是 key: %s, 新存放 Value: %s, 之前关联的 Value: %s \n",
                            args[0], // key
                            args[1], // new Value
                            returnValue // old Value
                    );
                }
            }
        });

        // 获取 代理对象
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        System.out.println("-----------------");
        proxy.put("1", "A1");
        System.out.println("-----------------");
        System.out.println(cache.get("1"));

    }
}
