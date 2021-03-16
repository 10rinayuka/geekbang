package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Random;

/**
 * @author riku
 * @Classname AspectXmlConfig
 * @Date 2021/3/12 0:56
 * @Description Aspect XML 配置类
 */
public class AspectXmlConfig {

    public void beforeAnyPublicMethod() {
        final Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For Purpose from XML configuration!");
        }
        System.out.println("@Before at ny public method(xml).");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around at ny public method: " + pjp.getSignature());
        return pjp.proceed();
    }

    /**
     * Join Point 拦截 后置动作
     */
    public void finalizedAnyPublicMethod() {
        System.out.println("@After at ny public method.");
    }

    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing at ny public method.");
    }

    public void afterReturningAnyPublicMethod() {
        System.out.println("@AfterReturning at ny public method.");
    }
}
