package org.geekbang.thinking.in.spring.ioc.abs.dependency.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @author riku
 * @Classname User
 * @Date 2020/10/31 0:31
 * @Description TODO
 */
public class User implements BeanNameAware {
    private Long id;

    private String name;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    private String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(0L);
        user.setName("default");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println("用户对象[" + beanName + "]初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("用户对象[" + beanName + "]销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
