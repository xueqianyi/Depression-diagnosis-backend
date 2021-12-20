package com.yiyuplatform.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/10/25
 * \* Time: 11:55
 * \* Description: 应用配置
 * \
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        NettySocketProperties.class,
})
public class NettySocketConfig {
    @Bean
    public SocketIOServer socketIOServer(NettySocketProperties nettySocketProperties) {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(nettySocketProperties.getHost());
        config.setPort(nettySocketProperties.getPort());
        config.setBossThreads(nettySocketProperties.getBossCount());
        config.setWorkerThreads(nettySocketProperties.getWorkCount());
        config.setAllowCustomRequests(nettySocketProperties.isAllowCustomRequests());
        config.setUpgradeTimeout(nettySocketProperties.getUpgradeTimeout());
        config.setPingTimeout(nettySocketProperties.getPingTimeout());
        config.setPingInterval(nettySocketProperties.getPingInterval());
        SocketIOServer server= new SocketIOServer(config);
        server.addNamespace("/chat");
        return server;
    }
}
