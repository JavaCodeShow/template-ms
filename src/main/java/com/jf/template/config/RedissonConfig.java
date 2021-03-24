package com.jf.template.config;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置
 *
 * @author 江峰
 * @date 2020/5/28 11:12 上午
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
@Slf4j
public class RedissonConfig {

    // @Resource
    // private RedissonProperties redissonProperties;
    //
    // @Bean
    // public RedissonClient redissonClient() {
    //     log.info("start initTransferBusinessFlow RedissonClient,config:{}", JsonUtil.toString(redissonProperties));
    //
    //     if (StringUtils.isBlank(redissonProperties.getNodes())) {
    //         throw new RuntimeException("redis nodes can't be null");
    //     }
    //
    //     Config config = new Config();
    //     String[] nodes = redissonProperties.getNodes().split(",");
    //
    //     if (nodes.length == 1) {
    //         SingleServerConfig serverConfig = config.useSingleServer().setAddress(nodes[0])
    //             .setPassword(
    //                 StringUtils.isBlank(redissonProperties.getPassword()) ? null : redissonProperties.getPassword())
    //             .setTimeout(redissonProperties.getTimeout()).setRetryAttempts(3)
    //             .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
    //             .setPingConnectionInterval(redissonProperties.getPingConnectionInterval())
    //             .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());
    //         if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
    //             serverConfig.setPassword(redissonProperties.getPassword());
    //         }
    //     } else {
    //         ClusterServersConfig serverConfig = config.useClusterServers().addNodeAddress(nodes)
    //             .setPassword(
    //                 StringUtils.isBlank(redissonProperties.getPassword()) ? null : redissonProperties.getPassword())
    //             .setTimeout(redissonProperties.getTimeout()).setScanInterval(redissonProperties.getScanInterval())
    //             .setPingConnectionInterval(redissonProperties.getPingConnectionInterval());
    //         if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
    //             serverConfig.setPassword(redissonProperties.getPassword());
    //         }
    //     }
    //     return Redisson.create(config);
    // }
}