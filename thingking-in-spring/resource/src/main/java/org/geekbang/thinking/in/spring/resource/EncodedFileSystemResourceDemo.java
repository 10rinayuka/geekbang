package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @author riku
 * @Classname EncodedFileSystemResourceDemo
 * @Date 2021/1/27 0:08
 * @Description 带有字符编码的 {@link FileSystemResource} 示例
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String curJavaFilePath = System.getProperty("user.dir") + "/thingking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        System.out.println(curJavaFilePath);

        File curJavaFile = new File(curJavaFilePath);
        // FileSystemResource -> WritableResource -> Resource
        FileSystemResource fileSystemResource = new FileSystemResource(curJavaFile);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");

        // 字符输入流
        Reader reader = encodedResource.getReader();
        System.out.println(IOUtils.toString(reader));
    }
}
