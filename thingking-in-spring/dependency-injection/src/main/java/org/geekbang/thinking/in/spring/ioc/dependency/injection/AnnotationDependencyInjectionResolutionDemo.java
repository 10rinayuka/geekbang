package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * @author riku
 * @Classname AnnotationDependencyInjectionResolutionDemo
 * @Date 2020/12/20 23:51
 * @Description 注解驱动的依赖注入的处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user; // 实时注入 + 类型（User.Class）查找 + 字段名称（user）

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional;

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 替换原有的注解，使用新注解
////        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
//        Set<Class<? extends Annotation>> annotationTypes = new LinkedHashSet<>();
//        annotationTypes.add(MyAutowired.class); // annotationTypes.add(Autowired.class);
//        annotationTypes.add(Inject.class);
//        annotationTypes.add(InjectedUser.class);
//        beanPostProcessor.setAutowiredAnnotationTypes(annotationTypes);
//        return beanPostProcessor;
//    }

    /**
     * 声明位static，优先加载 Bean
     * @return
     */
    @Bean
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {{
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 替换原有的注解，使用新注解
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }}

    public static final String xmlResourcePath = "classpath:/";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath + "META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("user: " + demo.user);
        System.out.println("injected user: " + demo.injectedUser);
        System.out.println(demo.equals(demo.injectedUser));

        System.out.println("-----------自定义注解-----------");
        System.out.println("optional user: " + demo.userOptional);
        System.out.println("my injected user: " + demo.myInjectedUser);


        applicationContext.close();
    }
}
