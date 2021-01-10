package org.geekbang.thinking.in.spring.ioc.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @author riku
 * @Classname MyDestructionAwareBeanPostProcessor
 * @Date 2021/1/10 22:35
 * @Description {@link DestructionAwareBeanPostProcessor} 实现
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    /**
     * DestructionAwareBeanPostProcessor 中必须实现的接口方法
     *
     * @param bean
     * @param beanName
     * @throws BeansException
     */
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("the user holder v9");
            System.out.println("postProcessBeforeDestruction() = " + userHolder.getDescription());
        }
    }
}
