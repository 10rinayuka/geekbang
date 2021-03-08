package org.geekbang.thinking.in.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author riku
 * @Classname TargetFilterDemo
 * @Date 2021/3/6 16:51
 * @Description AOP 目标过滤 示例
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassNm = "org.geekbang.thinking.in.spring.aop.overview.EchoService";

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        final Class<?> targetClass = classLoader.loadClass(targetClassNm);

        // 方法定义 String echo(String message)
        // Spring 反射工具类
        final Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        // 查找 方法 throw 类型
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerException && 参数类型为 String 的方法为: " + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                // 异常类型
                final Class<?>[] exceptionTypes = method.getExceptionTypes();
                // 参数类型
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (exceptionTypes.length == 1 && parameterTypes.length == 1
                        && NullPointerException.class.isAssignableFrom(exceptionTypes[0])
                        && String.class.isAssignableFrom(parameterTypes[0])) {
                    return true;
                }
                return false;
            }
        });
    }
}
