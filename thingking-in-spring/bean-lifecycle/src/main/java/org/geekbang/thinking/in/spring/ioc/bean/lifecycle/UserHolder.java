package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;

/**
 * @author riku
 * @Classname UserHolder
 * @Date 2021/1/6 23:46
 * @Description User Holder 类
 */
public class UserHolder {
    private final User user;
    private Integer number;
    private String description;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
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
}
