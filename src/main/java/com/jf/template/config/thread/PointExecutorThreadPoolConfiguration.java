package com.jf.template.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author: 江峰
 * @create: 2020-12-16 17:50
 * @since: 2.20.1
 */
@Configuration
// 利用@EnableAsync注解开启异步任务支持
@EnableAsync(proxyTargetClass = true)
@Slf4j
@EnableConfigurationProperties(PointExecutorThreadPoolProperties.class)
public class PointExecutorThreadPoolConfiguration {

    @Bean("pointsExecutorThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor pointsExecutorThreadPoolTaskExecutor(
            PointExecutorThreadPoolProperties threadPoolProperties) {

        log.info("start to initialize default thread pool for using.");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程名称前缀
        executor.setThreadNamePrefix(
                threadPoolProperties.getThreadNamePrefix());
        // 最大存活时间
        executor.setKeepAliveSeconds(
                threadPoolProperties.getKeepAliveSeconds());
        // 核心线程池数
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 最大线程
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 队列容量
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 线程被拒绝执行策略 - 在调用方线程上继续执行
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();

        return executor;
    }
}