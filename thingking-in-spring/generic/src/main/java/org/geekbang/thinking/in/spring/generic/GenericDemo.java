package org.geekbang.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author riku
 * @Classname GenericDemo
 * @Date 2021/2/7 23:47
 * @Description Java 泛型 示例
 */
public class GenericDemo {

    public static void main(String[] args) {
        // Java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
//        list.add(1); 编译错误

        // 泛型擦写
        Collection tmp = list;
        // 不会有编译错误 泛型擦写 欺骗编译器
        tmp.add(1);

        System.out.println(list);
    }
}
