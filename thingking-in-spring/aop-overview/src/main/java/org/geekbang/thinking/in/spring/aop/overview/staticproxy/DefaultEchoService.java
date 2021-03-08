package org.geekbang.thinking.in.spring.aop.overview.staticproxy;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;

/**
 * @author riku
 * @Classname DefaultEchoService
 * @Date 2021/3/6 16:31
 * @Description 默认 {@link EchoService} 实现
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO]" + message;
    }
}
