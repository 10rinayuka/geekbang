package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author riku
 * @Classname UserFactoryBean
 * @Date 2020/11/7 0:15
 * @Description {@link User} Bean 的 {@link FactoryBean} 实现
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
