package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @author riku
 * @Classname QualifierAnnotationDependencyInjectionDemo
 * @Date 2020/12/20 23:51
 * @Description {@link Qualifier} 注解依赖注入
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // primary SuperUser

    @Autowired
    @Qualifier("user")
    private User namedUser; // User

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Autowired
    @UserGroup("group2")
    private Collection<User> groupedUsers2;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(101L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(102L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(103L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(104L);
    }

    @Bean
    @UserGroup("group2")
    public User user5() {
        return createUser(105L);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);
        System.out.println(demo.namedUser);

//        System.out.println("---------------Qualifier Bean名称限定---------------");
//        System.out.println(applicationContext.getBean("user1"));
//        System.out.println(applicationContext.getBean("user2"));

        System.out.println("---------------Qualifier 逻辑分组---------------");
        // 预期输出4个 Bean： 实际只输出了2个 Bean（非Qualifier）
        System.out.println("all users: " + demo.allUsers);
        // 预期输出2个 Bean： user1、user2, 扩展了userGroup之后追加 user3、user4
        System.out.println("qualifier users: " + demo.qualifierUsers);
        // 预期输出2个 Bean： user3、user4
        System.out.println("grouped users: " + demo.groupedUsers);
        // user5
        System.out.println("grouped users2: " + demo.groupedUsers2);


        applicationContext.close();
    }
}
