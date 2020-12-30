package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;

/**
 * @author riku
 * @Classname UserFactory
 * @Date 2020/11/6 23:52
 * @Description {@link User} 工厂类
 */
public interface UserFactory {

    default User getUserInstance() {
        return new User();
    }
}
