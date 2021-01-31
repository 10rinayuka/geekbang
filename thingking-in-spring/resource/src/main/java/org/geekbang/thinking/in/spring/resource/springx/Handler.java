package org.geekbang.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author riku
 * @Classname Spring Handler
 * @Date 2021/1/31 23:48
 * @Description 简单继承 {@link sun.net.www.protocol.x.Handler}
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    public static void main(String[] args) throws IOException {
        // -Djava.protocol.handler.pkgs=org.geekbang.thinking.in.spring.resource

        // springx 协议
        // 类似于 classpath:/META-INF/production.properties
        URL url = new URL("springx:///META-INF/production.properties");
        InputStream inputStream = url.openStream();

        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
