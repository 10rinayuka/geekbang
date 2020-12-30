package org.geekbang.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @author riku
 * @Classname DependencySourceDemo
 * @Date 2020/12/24 0:51
 * @Description 依赖来源示例
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * @PostConstruct 晚于 @Autowired 字段注入（注入在postProcessProperties执行）
     */
    @PostConstruct
    public void init() {
        System.out.println("--------init--------");

        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        System.out.println("--------initByLookup--------");
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在 beanFactory 中查找！");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        System.out.println(demo.beanFactory);
        System.out.println(demo.resourceLoader);
        System.out.println(demo.applicationContext);
        System.out.println(demo.applicationEventPublisher);

        applicationContext.close();
    }
}
