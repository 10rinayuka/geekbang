package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @author riku
 * @Classname InjectingResourceDemo
 * @Date 2021/1/31 22:43
 * @Description 注入 {@link ResourceLoader} 示例
 * @see ResourceLoader
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("autowiredResourceLoader == resourceLoader : " + (autowiredResourceLoader == resourceLoader));
        System.out.println("autowiredResourceLoader == applicationContext : " + (autowiredResourceLoader == applicationContext));
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceLoaderDemo.class);

        context.refresh();

        context.close();
    }
}
