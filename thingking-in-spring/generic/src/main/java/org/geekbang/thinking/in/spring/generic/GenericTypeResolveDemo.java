package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author riku
 * @Classname GenericTypeResolveDemo
 * @Date 2021/2/8 23:08
 * @Description {@link GenericTypeResolver} 示例
 * @see GenericTypeResolver
 */
public class GenericTypeResolveDemo implements Comparable<GenericTypeResolveDemo> {
    @Override
    public int compareTo(GenericTypeResolveDemo o) {
        return 0;
    }

    public static void main(String[] args) throws NoSuchMethodException {

        // String 是 Comparable<String> 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, Comparable.class, "getString");

        // List<Object> 是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getList");

        // StringList 也是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getStringList");

        // 泛型参数具体化（字节码记录）, 具备 ParameterizedType 返回，否则返回 null

        // TypeVariable
        System.out.println(GenericTypeResolver.getTypeVariableMap(StringList.class));
        System.out.println(GenericTypeResolver.getTypeVariableMap(GenericTypeResolveDemo.class));

    }

    public static String getString() {
        return null;
    }

    public static List<Object> getList() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentTypes)
            throws NoSuchMethodException {
        final Method method = containingClass.getMethod(methodName, argumentTypes);

        // 声明类 GenericTypeResolveDemo.class
        final Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);

        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n",
                methodName, containingClass.getSimpleName(), returnType);

        // 常规类型不具备泛型参数类型 List<E>
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n",
                methodName, containingClass.getSimpleName(),
                GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc));
    }

}
