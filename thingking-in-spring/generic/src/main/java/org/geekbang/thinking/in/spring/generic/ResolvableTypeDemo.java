package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * @author riku
 * @Classname ResolvableTypeDemo
 * @Date 2021/2/9 0:20
 * @Description {@link ResolvableType} 示例
 * @see ResolvableType
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {

        // 工厂创建
        // StringList <- ArrayList<String> <- AbstractList<String> <- List<String> <- Collection<String>
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        // 获取 raw type
        System.out.println(resolvableType.asCollection().resolve());
        // 获取泛型类型参数
        System.out.println(resolvableType.asCollection().resolveGeneric(0));

    }

}
