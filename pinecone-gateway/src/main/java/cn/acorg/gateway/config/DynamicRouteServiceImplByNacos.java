package cn.acorg.gateway.config;

import cn.acorg.gateway.service.DynamicRouteServiceImpl;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 动态路由，SpringCloud 启动后自动运行，监听 nacos中心的配置发布
 * @author 松果
 * @date 2020/11/2 15:06
 * @version 1.0
 */
@Slf4j
@Component
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @Autowired
    private NacosGatewayProperties nacosGatewayProperties;

    /**
     * 监听Nacos Server下发的动态路由配置
     * @author 松果
     * @date 2020/11/2 15:07
     * @return void
     */
    public void dynamicRouteByNacosListener() {
        try {
            // Nacos 配置初始化
            Properties properties = new Properties();
            properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosGatewayProperties.getAddress());
            properties.setProperty(PropertyKeyConst.NAMESPACE, nacosGatewayProperties.getNamespace());

            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroup(), nacosGatewayProperties.getTimeout());
            log.info("读取到的初始配置：\n{}", content);
            List<RouteDefinition> list = JSON.parseArray(content, RouteDefinition.class);
            list.forEach(definition -> dynamicRouteService.add(definition));
            log.info("路由初始化完成");

            // 对 nacos 配置中心的 网关路由配置 进行监听
            configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroup(), new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("监听到动态更新，最新路由配置：\n{}", configInfo);
                    List<RouteDefinition> list = JSON.parseArray(configInfo, RouteDefinition.class);
                    list.forEach(definition -> dynamicRouteService.update(definition));
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            log.info("nacos 动态路由出现异常，异常码：{}：异常提示：\n{} ", e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        dynamicRouteByNacosListener();
    }
}
