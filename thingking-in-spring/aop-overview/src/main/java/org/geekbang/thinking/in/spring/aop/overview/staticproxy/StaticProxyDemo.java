package org.geekbang.thinking.in.spring.aop.overview.staticproxy;

/**
 * @author riku
 * @Classname StaticProxyDemo
 * @Date 2021/3/6 16:30
 * @Description 静态代理 示例
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        final ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello World!");
    }
}
