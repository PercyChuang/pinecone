package cn.acorg.client.netty.factory;

import cn.acorg.client.netty.fallback.NettyClientFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 松果
 * @version 1.0
 * @date 2020/10/31 13:54
 */
@Slf4j
@Component
public class NettyClientFactory implements FallbackFactory<NettyClientFallback> {

    private final NettyClientFallback nettyClientFallback;

    public NettyClientFactory(NettyClientFallback nettyClientFallback) {
        this.nettyClientFallback = nettyClientFallback;
    }
    @Override
    public NettyClientFallback create(Throwable throwable) {
        log.error("NettyClientFactory - 这是触发的异常信息: {}", throwable.getMessage());
        return nettyClientFallback;
    }
}
