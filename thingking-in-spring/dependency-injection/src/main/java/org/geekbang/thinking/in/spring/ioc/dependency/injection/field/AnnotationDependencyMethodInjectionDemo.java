package org.geekbang.thinking.in.spring.ioc.dependency.injection.field;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author riku
 * @Classname AnnotationDependencyMethodInjectionDemo
 * @Date 2020/12/7 23:19
 * @Description 基于 Java 注解 依赖方法注入 示例
 */
public class AnnotationDependencyMethodInjectionDemo {

    public static final String xmlResourcePath = "classpath:/";

    private UserHolder userHolder;
    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // @Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);

        System.out.println(demo.userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
