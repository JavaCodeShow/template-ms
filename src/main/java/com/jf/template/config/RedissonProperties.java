package com.jf.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson分布式锁配置
 *
 * @author zhangyunhe
 * @date 2020/6/19 11:47 上午
 */
@Data
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {
    /**
     * 节点信息 host:port
     */
    private String nodes;
    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间
     */
    private Integer timeout = 3000;

    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    private Integer scanInterval = 1000;
    /**
     * 连接池大小
     */
    private Integer connectionPoolSize = 64;
    /**
     * 最小空闲连接
     */
    private Integer connectionMinimumIdleSize = 10;

    /**
     *
     */
    private Integer pingConnectionInterval = 1000;

}