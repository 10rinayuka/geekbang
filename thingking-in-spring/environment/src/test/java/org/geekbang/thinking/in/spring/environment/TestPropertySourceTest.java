package org.geekbang.thinking.in.spring.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author riku
 * @Classname TestPropertySourceTest
 * @Date 2021/2/26 22:38
 * @Description {@link TestPropertySource} 测试 示例
 * @see TestPropertySource
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class) // Spring 注解驱动测试注解
@TestPropertySource(
        properties = "user.name = 花音", // Inlined Test Properties
        locations = "classpath:/META-INF/test.properties" // class path resource
)
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        System.out.println(this.userName);
        Assert.assertEquals("花音", userName);

        // 优先级查看
        for (PropertySource<?> ps : environment.getPropertySources()) {
            System.out.printf("propertySources(name=%s) 'user.name' 属性: %s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }
}
