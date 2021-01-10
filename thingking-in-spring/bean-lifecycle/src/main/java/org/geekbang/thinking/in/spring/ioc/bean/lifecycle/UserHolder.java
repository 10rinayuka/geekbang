package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author riku
 * @Classname UserHolder
 * @Date 2021/1/6 23:46
 * @Description User Holder 类
 */
public class UserHolder implements BeanFactoryAware, BeanNameAware, BeanClassLoaderAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton, DisposableBean {
    private final User user;
    private Integer number;
    private String description;

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }

    /**
     * Aware 接口 回调 示例
     */
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initPostConstructor() {
        // initPostConstructor v3 -> v4
        this.description = "user holder v4";
        System.out.println("initPostConstructor() = " + this.description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // afterPropertiesSet v4 -> v5
        this.description = "user holder v5";
        System.out.println("afterPropertiesSet() = " + this.description);
    }

    public void init() {
        // init v5 -> v6
        this.description = "user holder v6";
        System.out.println("init() = " + this.description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        // afterSingletonsInstantiated v7 -> v8
        this.description = "user holder v8";
        System.out.println("afterSingletonsInstantiated() = " + this.description);
    }

    // Bean 销毁方法

    @PreDestroy
    public void preDestroy() {
        // preDestroy v9 -> v10
        this.description = "user holder v10";
        System.out.println("preDestroy() = " + this.description);
    }

    @Override
    public void destroy() throws Exception {
        // destroy v10 -> v11
        this.description = "user holder v11";
        System.out.println("destroy() = " + this.description);
    }

    public void doDestroy() {
        // destroy v11 -> v12
        this.description = "user holder v12";
        System.out.println("doDestroy() = " + this.description);
    }

    // GC
    @Override
    protected void finalize() throws Throwable {
        System.out.println("UserHolder is finalize...");
    }
}
