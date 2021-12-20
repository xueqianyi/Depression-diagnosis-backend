package com.yiyuplatform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/11/6
 * \* Time: 19:25
 * \* Description: 用于获取Application.yml里的property
 * \
 */
@Data
@ConfigurationProperties("ws")
public class NettySocketProperties {
    /**
     * title
     */
    private String title;

    /**
     * host
     */
    private String host;

    /**
     * port
     */
    private Integer port;

    /**
     * bossCount
     */
    private int bossCount;

    /**
     * workCount
     */
    private int workCount;

    /**
     * allowCustomRequests
     */
    private boolean allowCustomRequests;

    /**
     * upgradeTimeout
     */
    private int upgradeTimeout;

    /**
     * pingTimeout
     */
    private int pingTimeout;

    /**
     * pingInterval
     */
    private int pingInterval;
}
