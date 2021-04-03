package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author riku
 * @Classname MyThrowsAdvice
 * @Date 2021/3/22 0:36
 * @Description TODO
 */
public class MyThrowsAdvice implements ThrowsAdvice {

    // 1个参数
//    public void afterThrowing(RuntimeException e) {
//        System.out.printf("exception: %s\n", e);
//    }

    // 4个参数
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.printf("Method: %s, arg: %s, target: %s, exception: %s\n",
                method, Arrays.asList(args), target, e);
    }
}
