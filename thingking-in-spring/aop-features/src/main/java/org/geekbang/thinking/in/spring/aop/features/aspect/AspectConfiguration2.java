package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author riku
 * @Classname AspectConfiguration
 * @Date 2021/3/11 0:23
 * @Description Aspect 配置类
 */
@Aspect
@Order
public class AspectConfiguration2 implements Ordered {

    /**
     * Join Point 拦截方法
     */
    @Before("execution(public * *(..))")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before at ny public method(2).");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
