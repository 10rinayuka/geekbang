package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author riku
 * @Classname AspectXmlConfig
 * @Date 2021/3/12 0:56
 * @Description Aspect XML 配置类
 */
public class AspectXmlConfig {

    public void beforeAnyPublicMethod() {
        System.out.println("@Before at ny public method(xml).");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around at ny public method: " + pjp.getSignature());
        return pjp.proceed();
    }
}
