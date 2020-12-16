package com.jf.template.config.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述: 线程池配置参数
 *
 * @author: 江峰
 * @create: 2020-12-16 17:50
 * @since: 2.20.1
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PointExecutorThreadPoolProperties.PREFIX)
public class PointExecutorThreadPoolProperties {

    public static final String PREFIX = "point.executor.thread.pool";

    /**
     * 线程名称前缀
     */
    private String threadNamePrefix;

    /**
     * 核心线程池数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize;

    /**
     * 等待队列大小
     */
    private Integer queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private Integer keepAliveSeconds;

}