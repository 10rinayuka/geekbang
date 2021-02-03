package org.geekbang.thinking.in.spring.il8n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @author riku
 * @Classname DynamicResourceMessageSource
 * @Date 2021/2/3 1:03
 * @Description 动态（更新）资源 {@link org.springframework.context.MessageSource} 实现
 * @see org.springframework.context.MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourcePath = "/META-INF/msg.properties";

    public static final String ENCODING = "UTF-8";

    private final Properties messageProperties;

    private final Resource messagePropertiesResource;

    public final ExecutorService executorService;

    private ResourceLoader resourceLoader;


    public DynamicResourceMessageSource() {
        // 获取资源位置
        this.messagePropertiesResource = getMessagePropertiesResource();
        // 初始化 Properties 对象
        this.messageProperties = loadMessageProperties();
        // 实现线程池处理文件变化（拉取事件时，同步阻塞当前线程）
        this.executorService = Executors.newSingleThreadExecutor();

        // 监听资源文件 （Java NIO 2 WatchService）
        onMessagePropertiesChanged();
    }

    /**
     * 监听资源文件 （Java NIO 2 WatchService）
     */
    private void onMessagePropertiesChanged() {
        if (this.messagePropertiesResource.isFile()) {

            // 获取对应文件系统中的文件
            try {
                File messagePropertiesResourceFile = this.messagePropertiesResource.getFile();
                Path messagePropertiesPath = messagePropertiesResourceFile.toPath();

                // 获取当前 OS 文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 新建 WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 注册 WatchService 到 messagePropertiesResourceFile，并且关心修改事件
                Path dirPath = messagePropertiesPath.getParent();
                dirPath.register(watchService, ENTRY_DELETE);

                // 处理资源文件变化（异步）
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * 处理资源文件变化（异步）
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        this.executorService.submit(() -> {
            while (true) {
                // take 发生阻塞
                WatchKey watchKey = watchService.take();
                // watchKey 是否有效
                try {
                    if (watchKey.isValid()) {
                        for (WatchEvent<?> event : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            // 监听的注册目录
                            Path dirPath = (Path) watchable;
                            // 事件所关联的对象即注册目录的子文件（或者子目录）
                            // 事件发生源时相对路径
                            Path fileRelativePath = (Path) event.context();
                            // 处理为 绝对路径
                            Path filePath = dirPath.resolve(fileRelativePath);

                            System.out.println("修改的文件: " + filePath);
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        // 重置 watchKey
                        System.out.println("watchKey reset");
                        watchKey.reset();
                    }
                }
            }
        });
    }

    /**
     * 加载 Properties 对象
     *
     * @return
     */
    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);

        Properties properties = new Properties();
        try (Reader reader = encodedResource.getReader()) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        final String messageFormatPattern = this.messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }


    public static void main(String[] args) {
        final DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        final String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.println(message);
    }
}
