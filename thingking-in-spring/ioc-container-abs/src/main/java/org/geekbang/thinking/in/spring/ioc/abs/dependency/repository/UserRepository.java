package org.geekbang.thinking.in.spring.ioc.abs.dependency.repository;

import com.sun.glass.ui.Application;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author riku
 * @Classname UserRepository
 * @Date 2020/11/1 13:58
 * @Description 用户信息仓库
 */
public class UserRepository {

    private Collection<User> users; // 定义Bean

    private BeanFactory beanFactory; // 内建非Bean 对象

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> appObjectFactory;

    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ObjectFactory<ApplicationContext> getAppObjectFactory() {
        return appObjectFactory;
    }

    public void setAppObjectFactory(ObjectFactory<ApplicationContext> appObjectFactory) {
        this.appObjectFactory = appObjectFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
