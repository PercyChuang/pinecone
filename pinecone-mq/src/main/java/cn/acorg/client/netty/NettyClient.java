package cn.acorg.client.netty;

import cn.acorg.client.netty.factory.NettyClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 松果
 * @version 1.0
 * @date 2020/10/31 11:12
 */
@FeignClient(name = "pinecone-netty", fallbackFactory = NettyClientFactory.class)
public interface NettyClient {

    /**
     * 测试服务调用
     * @author 松果
     * @date 2020/10/31 13:52
     * @return java.lang.String
     */
    @PostMapping("/test/test2")
    String test2(List<String> list);
}
