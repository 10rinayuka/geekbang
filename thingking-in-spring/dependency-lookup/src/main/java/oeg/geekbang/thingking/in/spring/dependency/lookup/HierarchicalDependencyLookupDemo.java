package oeg.geekbang.thingking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author riku
 * @Classname HierarchicalDependencyLookupDemo
 * @Date 2020/11/18 0:21
 * @Description 层次性依赖查找示例
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);

        // 1. 获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: " + beanFactory.getParentBeanFactory());

        // 2. 设置 Parent BeanFactory
        beanFactory.setParentBeanFactory(createParentBeanFactory());
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: " + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean((HierarchicalBeanFactory) beanFactory.getParentBeanFactory(), "user");

        displayLocalBean(beanFactory, "user");
        displayLocalBean((HierarchicalBeanFactory) beanFactory.getParentBeanFactory(), "user");

        // 启动应用上下文
        applicationContext.refresh();

        // 关闭应用上下文
        applicationContext.close();

    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n",
                beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            // 递归判断
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local bean[name : %s] : %s\n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载资源
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }
}
