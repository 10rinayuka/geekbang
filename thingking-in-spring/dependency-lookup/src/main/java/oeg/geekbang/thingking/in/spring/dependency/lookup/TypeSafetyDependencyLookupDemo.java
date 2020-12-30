package oeg.geekbang.thingking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author riku
 * @Classname TypeSafetyDependencyLookupDemo
 * @Date 2020/11/23 1:10
 * @Description 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找
        // BeanFactory#getBean
        displayBeanFactoryGetBean(applicationContext);
        // ObjectFactory#getObject
        displayObjectFactoryGetObject(applicationContext);
        // ObjectProvider#getIfAvailable
        displayObjectProviderGetIfAvailable(applicationContext);

        // 集合类型查找
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();

    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> beanProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("-------------------------------------");
        System.err.println("Source from: " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
