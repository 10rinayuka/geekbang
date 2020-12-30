package org.geekbang.thinking.in.spring.ioc.abs.dependency.domain;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.annotation.Super;

/**
 * @author riku
 * @Classname SuperUser
 * @Date 2020/10/31 0:54
 * @Description TODO
 */
@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
