package cn.acorg.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Nacos 网关配置
 * @author 松果
 * @date 2020/11/2 15:06
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix="nacos")
@RefreshScope
public class NacosGatewayProperties {

    /**
     * Nacos ServerAddr
     */
    private String address;

    /**
     * Nacos 命名空间
     */
    private String namespace;

    /**
     * 服务 DataId
     */
    private String dataId;

    /**
     * 服务 group
     */
    private String group;

    /**
     * 动态路由获取超时时间
     */
    private Long timeout;

}
