package org.geekbang.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author riku
 * @Classname UsersNamespacsHandler
 * @Date 2021/1/20 0:24
 * @Description TODO
 */
public class UsersNamespacsHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        // 将 "user" 元素注册对应的 BeanDefinitionParser 实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
