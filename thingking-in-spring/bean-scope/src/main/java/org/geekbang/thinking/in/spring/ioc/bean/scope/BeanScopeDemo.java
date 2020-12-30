package org.geekbang.thinking.in.spring.ioc.bean.scope;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @author riku
 * @Classname DependencySourceDemo
 * @Date 2020/12/24 0:51
 * @Description Bean 的作用域示例
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; // Resolve Dependency

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        // 添加
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称：%s 在初始化之后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        // 启动应用上下文
        applicationContext.refresh();
        System.out.println("--------依赖查找--------");
        scopeBeansByLookup(applicationContext);
        System.out.println("--------依赖注入--------");
        scopeBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("singletonUser = " + singletonUser);
            System.out.println("prototypeUser = " + prototypeUser);
        }
    }

    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println("demo.singletonUser = " + demo.singletonUser);
        System.out.println("demo.singletonUser1 = " + demo.singletonUser1);
        System.out.println("demo.prototypeUser = " + demo.prototypeUser);
        System.out.println("demo.prototypeUser1 = " + demo.prototypeUser1);
        System.out.println("demo.prototypeUser2 = " + demo.prototypeUser2);

        System.out.println("demo.users = " + demo.users);
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        for (Map.Entry<String, User> userEntry : users.entrySet()) {
            String beanName = userEntry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                User user = userEntry.getValue();
                user.destroy();
            }
        }

        System.out.println("当前 BeanScopeDemo Bean 正在销毁完成");
    }
}
