package cn.acorg.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Netty 测试服务端
 * @author ChenXueSong
 * @datetime 2020/7/30 20:43
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                log.debug("Netty-客户端发送数据包[id:{}]：\n{}", ctx.channel().id(), msg);
                                ByteBuf byteBuf = Unpooled.copiedBuffer("This is Read", StandardCharsets.UTF_8);
                                ctx.channel().writeAndFlush(byteBuf);
                            }

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                log.debug("新的连接接入：id:{}, remoteAddress:{}, localAddress:{}", ctx.channel().id(),
                                        ctx.channel().localAddress(), ctx.channel().remoteAddress());
                                ByteBuf byteBuf = Unpooled.copiedBuffer("This is Active", StandardCharsets.UTF_8);
                                ctx.channel().writeAndFlush(byteBuf);
                            }


                        });
                    }
                }).bind(8101);
    }
}
