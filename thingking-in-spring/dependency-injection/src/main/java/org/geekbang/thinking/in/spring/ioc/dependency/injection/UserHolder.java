package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;

/**
 * @author riku
 * @Classname UserHolder
 * @Date 2020/12/7 23:13
 * @Description {@link User} 的 Holder 类
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
