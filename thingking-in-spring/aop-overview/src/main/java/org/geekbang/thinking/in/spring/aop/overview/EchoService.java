package org.geekbang.thinking.in.spring.aop.overview;

/**
 * @author riku
 * @Classname EchoService
 * @Date 2021/3/6 16:31
 * @Description Echo 服务
 */
public interface EchoService {
    String echo(String message) throws NullPointerException;
}
