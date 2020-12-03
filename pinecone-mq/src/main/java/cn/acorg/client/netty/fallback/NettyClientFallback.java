package cn.acorg.client.netty.fallback;

import cn.acorg.client.netty.NettyClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 松果
 * @version 1.0
 * @date 2020/10/31 13:45
 */
@Component
public class NettyClientFallback implements NettyClient {


    /**
     *
     * @param list
     * @author 松果
     * @date 2020/11/9 11:10
     * @return java.lang.String
     */
    @Override
    public String test2(List<String> list) {

        return "服务提供方异常：已触发：NettyClientFallback";
    }
}
