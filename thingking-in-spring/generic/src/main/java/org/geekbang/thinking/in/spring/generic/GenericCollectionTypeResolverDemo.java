package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author riku
 * @Classname GenericCollectionTypeResolverDemo
 * @Date 2021/2/8 23:50
 * @Description {@link GenericCollectionTypeResolver} 示例
 * @see GenericCollectionTypeResolver
 */
public class GenericCollectionTypeResolverDemo {

    private static StringList stringList;

    private static List<Object> objects;

    public static void main(String[] args) throws Exception {

        // getCollectionType 返回具体化泛型参数类型的集合的成员类型
        // StringList -> String
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        // ArrayList -> null
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        // 获取字段
        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("objects");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        System.out.println("get" + StringUtils.capitalize("cstNum"));

    }
}
