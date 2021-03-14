package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author riku
 * @Classname AspectConfiguration
 * @Date 2021/3/11 0:23
 * @Description Aspect 配置类
 */
@Aspect
@Order
public class AspectConfiguration {

    /**
     * 匹配 Join Point
     */
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
        System.out.println("@Pointcut at ny public method.");
    }

    /**
     * Join Point 前置 拦截方法
     */
    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before at ny public method.");
    }

    /**
     * Around 方法 - 需要显示的去调用 proceed 方法
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("anyPublicMethod()")
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around at ny public method start.");
        final Object ret = pjp.proceed();
        System.out.println("@Around at ny public method end.");

        return ret;
    }

    /**
     * Join Point 拦截 后置动作
     */
    @After("anyPublicMethod()")
    public void finalizedAnyPublicMethod() {
        System.out.println("@After at ny public method.");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing at ny public method.");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterReturningAnyPublicMethod() {
        System.out.println("@AfterReturning at ny public method.");
    }
}
