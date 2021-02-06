package org.geekbang.thinking.in.spring.ioc.abs.dependency.domain;

/**
 * @author riku
 * @Classname Company
 * @Date 2021/2/4 23:25
 * @Description 公司类
 */
public class Company {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
