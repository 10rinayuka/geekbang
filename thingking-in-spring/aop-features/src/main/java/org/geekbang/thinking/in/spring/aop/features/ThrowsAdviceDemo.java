package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.aspect.MyThrowsAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @author riku
 * @Classname ThrowsAdviceDemo
 * @Date 2021/3/22 0:28
 * @Description TODO
 */
public class ThrowsAdviceDemo {

    public static void main(String[] args) {

        ThrowsAdviceDemo instance = new ThrowsAdviceDemo();
        ProxyFactory proxyFactory = new ProxyFactory(instance);

        // 增加 ThrowsAdvice 拦截方法
        proxyFactory.addAdvice(new MyThrowsAdvice());

        ThrowsAdviceDemo proxy = (ThrowsAdviceDemo) proxyFactory.getProxy();
        proxy.execute();
        proxy.execute();
    }

    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For purpose");
        }

        System.out.println("executing...");
    }
}
