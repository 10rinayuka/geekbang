package org.geekbang.thinking.in.spring.ioc.dependency.injection.constructor;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author riku
 * @Classname ApiDependencyConstructorInjectionDemo
 * @Date 2020/12/7 23:19
 * @Description 基于 API Constructor 方法注入示例
 */
public class ApiDependencyConstructorInjectionDemo {

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
        definitionBuilder.addConstructorArgReference("superUser");
        return definitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user) {
//        return new UserHolder(user);
//    }
}
