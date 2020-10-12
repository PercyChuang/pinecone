package cn.acorg.gateway.config;

import cn.acorg.gateway.service.DynamicRouteServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 动态路由，SpringCloud 启动后自动运行，监听nacos中心的配置发布
 * @author ChenXueSong
 * @date 2020-09-29 21:12
 */
@Slf4j
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @Autowired
    private NacosGatewayProperties nacosGatewayProperties;

    /**
     * 监听Nacos Server下发的动态路由配置
     * @return void
     * @author ChenXueSong
     * @date 2020-09-29 21:25
     */
    public void dynamicRouteByNacosListener (){
        try {
            ConfigService configService= NacosFactory.createConfigService(nacosGatewayProperties.getAddress());
            String content = configService.getConfig(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), nacosGatewayProperties.getTimeout());
            log.info("读取到的初始配置：\n{}", content);
            configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> list = JSON.parseArray(configInfo, RouteDefinition.class);
                    list.forEach(definition-> dynamicRouteService.update(definition));
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
