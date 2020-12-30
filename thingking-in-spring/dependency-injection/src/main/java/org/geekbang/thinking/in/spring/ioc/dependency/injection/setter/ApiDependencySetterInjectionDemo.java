package org.geekbang.thinking.in.spring.ioc.dependency.injection.setter;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author riku
 * @Classname AnnotationDependencySetterInjectionDemo
 * @Date 2020/12/7 23:19
 * @Description 基于 API Setter 方法注入示例
 */
public class ApiDependencySetterInjectionDemo {

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 UserHolder 的 BeanDefinition
        BeanDefinition userHolderDefinition = createUserHolderDefinition();
        applicationContext.registerBeanDefinition("userHolder", userHolderDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        SuperUser superUser = applicationContext.getBean(SuperUser.class);
        superUser.setId(2L);
        superUser.setName("riku222");
        superUser.setAddress("嘉兴");
        System.out.println("SuperUser Change!!!");
        System.out.println(userHolder);


        applicationContext.close();
    }

    /**
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     *
     * @return
     */
    private static BeanDefinition createUserHolderDefinition() {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
//        definitionBuilder.addPropertyValue()
        definitionBuilder.addPropertyReference("user", "superUser");
        return definitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user) {
//        return new UserHolder(user);
//    }
}
