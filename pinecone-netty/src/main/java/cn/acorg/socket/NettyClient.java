package cn.acorg.socket;

import cn.acorg.socket.handler.Pinger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Netty socket 连接
 * @author ChenXueSong
 * @datetime 2020/7/30 20:03
 */
@Slf4j
@Component
public class NettyClient {

    /** ip地址 */
    private String host;
    /** 端口号 */
    private int port;
    /** 通信通道 */
    private Channel channel;
    /** 客户端的 NIO线程组 */
    private EventLoopGroup group;
    /** 客户端辅助启动类 对象 */
    private Bootstrap bootstrap;
    /** 通道回调监听器 */
    private ChannelFutureListener channelFutureListener = null;

    /**
     * 连接 socket 服务端
     *
     * @param host ip
     * @param port 端口号
     * @return void
     * @author ChenXueSong
     * @datetime 2020/8/4 16:21
     */
    public void connect(String host, int port) throws InterruptedException {
        this.host = host;
        this.port = port;
        // 配置 客户端的 NIO线程组
        group = new NioEventLoopGroup();
        // 客户端启动辅助类
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                // 小数据即时发送
                .option(ChannelOption.TCP_NODELAY, true)
                // 两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024 * 1024))
                // 连接超时 时间设置
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        // 解码器，编码 UTF-8
                        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                        // 编码器，编码 UTF-8
                        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                        // 设置读写超时时间，读超时 60秒，写超时 5秒
                        ch.pipeline().addLast(new IdleStateHandler(60,10,0, TimeUnit.SECONDS));
                        // 心跳触发器
                        ch.pipeline().addLast(new Pinger());
                        // 接收器
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                log.debug("Netty-服务端回复数据包：\n{}", msg);
                            }
                        });
                    }
                });

        // 发起连接，获取连接通道
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        channel= channelFuture.channel();
        channelFutureListener = future -> {
            if (future.isSuccess())  {
                channel = future.channel();
                if (channel.isActive()) {
                    log.debug("重新连接服务器成功");
                } else {
                    log.debug("重新连接服务器失败，5秒后重新连接");
                    //  5秒后重新连接
                    future.channel().eventLoop().schedule(this::reconnection, 5, TimeUnit.SECONDS);
                }
             } else {
                log.debug("重新连接服务器失败，5秒后重新连接");
                //  5秒后重新连接
                future.channel().eventLoop().schedule(this::reconnection, 5, TimeUnit.SECONDS);
            }
        };
        channel.closeFuture().addListener(channelFutureListener);
    }

    /**
     * 通道重连
     *
     * @return void
     * @author ChenXueSong
     * @datetime 2020/8/4 18:20
     */
    private void reconnection() {
        // 发起连接，获取连接通道
        ChannelFuture future = bootstrap.connect(this.host, this.port);
        channel = future.channel();
        // 添加 回调监听
        channel.closeFuture().addListener(channelFutureListener);
    }

    /**
     * 通过 socket 通信通道，发送内容到 银行服务端
     *
     * @param message 内容
     * @return void
     * @author ChenXueSong
     * @datetime 2020/8/4 16:21
     */
    public void sender(String message) {
        group.next().execute(()-> {
            log.debug("Netty-客户端发送数据包：\n{}", message);
            ChannelFuture channelFuture = channel.writeAndFlush(message);
            channelFuture.addListener(future -> {
                log.debug("客户端发送数据包完成");
            });
        });
    }
}
