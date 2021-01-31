package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @author riku
 * @Classname InjectingResourceDemo
 * @Date 2021/1/31 22:43
 * @Description 注入 {@link org.springframework.core.io.Resource} 示例
 * @see org.springframework.core.io.Resource
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("${user.dir}")
    private String currentProjectBasePath;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResources;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("=============");
        Stream.of(propertiesResources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("=============");
        System.out.println(currentProjectBasePath);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);

        context.refresh();

        context.close();
    }
}
