package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author riku
 * @Classname EncodedFileSystemResourceDemo
 * @Date 2021/1/27 0:08
 * @Description 带有字符编码的 {@link FileSystemResourceLoader} 示例
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String curJavaFilePath = System.getProperty("user.dir") + "/thingking-in-spring/resource/" +
                "src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        System.out.println(curJavaFilePath);

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(curJavaFilePath);

        // FileSystemResource -> WritableResource -> Resource
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        // 字符输入流
        // 自当关闭资源 try...with
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
