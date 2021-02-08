package org.geekbang.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author riku
 * @Classname GenericAPIDemo
 * @Date 2021/2/8 0:03
 * @Description Java 泛型 API 示例
 */
public class GenericAPIDemo {

    public static void main(String[] args) {
        // 原生类型 primitive - type int long floot
        Class intClass = int.class;

        // 数组类型 array type - int[], Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 raw type - String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized type
        final ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        System.out.println(ArrayList.class);
        System.out.println(parameterizedType.getTypeName());
        System.out.println(parameterizedType.toString());

        // 泛型类型变量 Type Variable
        System.out.println("raw type: " + parameterizedType.getRawType());
        final Type[] typeVariables = parameterizedType.getActualTypeArguments();

        Stream.of(typeVariables)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);
    }
}
