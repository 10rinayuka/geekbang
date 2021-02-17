package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld 模块 {@link ImportSelector}
 *
 * @author jay
 * @date 2021/02/14
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 导入
        return new String[] {"org.geekbang.thinking.in.spring.annotation.HelloWorldClass"};
    }
}
