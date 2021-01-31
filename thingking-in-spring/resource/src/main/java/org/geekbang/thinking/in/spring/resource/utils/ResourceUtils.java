package org.geekbang.thinking.in.spring.resource.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author riku
 * @Classname ResourceUtils
 * @Date 2021/1/31 22:26
 * @Description {@link javax.annotation.Resource} 工具类
 */
public interface ResourceUtils {

    /**
     * UTF-8 方式获取资源
     *
     * @param resource
     * @return
     * @throws IOException
     */
    static String getContent(Resource resource) {
        try {
            return getContent(resource, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取资源
     *
     * @param resource
     * @param encoding
     * @return
     * @throws IOException
     */
    static String getContent(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);

        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }

    }
}
