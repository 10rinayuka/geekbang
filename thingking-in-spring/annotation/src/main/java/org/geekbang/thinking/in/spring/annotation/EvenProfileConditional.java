package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author riku
 * @Classname EvenConditional
 * @Date 2021/2/18 22:58
 * @Description 偶数 Profile 条件
 */
public class EvenProfileConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 条件上下文
        final Environment environment = context.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
