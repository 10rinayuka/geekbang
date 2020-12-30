package oeg.geekbang.thingking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author riku
 * @Classname ObjectProviderDemo
 * @Date 2020/11/17 1:25
 * @Description {@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找
        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);

        lookupByStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();

    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println("-------------------------------------");
//        for (String str : stringObjectProvider) {
//            System.out.println(str);
//        }
        stringObjectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象：" + user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello, World!";
    }

    @Bean
    public String message() {
        return "Message";
    }
}
