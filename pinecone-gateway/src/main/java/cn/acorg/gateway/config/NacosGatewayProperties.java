package cn.acorg.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author ChenXueSong
 * @date 2020-09-29 21:12
 */
@Data
@ConfigurationProperties(prefix="nacos")
@Configuration
public class NacosGatewayProperties {

    /**
     * Nacos ServerAddr
     */
    private String address;

    /**
     * 服务 DataId
     */
    private String dataId;

    /**
     * 服务 groupId
     */
    private String groupId;

    /**
     * 动态路由获取超时时间
     */
    private Long timeout;

}
