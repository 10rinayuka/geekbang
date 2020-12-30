package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author riku
 * @Classname DefaultUserFactory
 * @Date 2020/11/6 23:53
 * @Description {@link UserFactory} 默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    @Override
    public User getUserInstance() {
        User user = new User();
        user.setId(1L);
        user.setName("default-instance-riku");
        return user;
    }

    /**
     * 1. 基于 @PostConstruct 注解
     */
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct UserFactory 初始化中...");
    }

    /**
     * 2. 自定义 Bean 初始化方法
     */
    public void initUserFactory() {
        System.out.println("自定义初始化方法initUserFactory: UserFactory 初始化中...");
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现InitializingBean afterPropertiesSet方法: UserFactory 初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("实现DisposableBean destroy: UserFactory 销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法 doDestroy: UserFactory 销毁中...");
    }
}
