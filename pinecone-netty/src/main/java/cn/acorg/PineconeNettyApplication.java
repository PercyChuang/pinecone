package cn.acorg;

import cn.acorg.socket.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PineconeNettyApplication implements ApplicationRunner {

    @Autowired
    private NettyClient nettyClient;

    public static void main(String[] args) {
        SpringApplication.run(PineconeNettyApplication.class, args);
    }


    /**
     * springboot 启动后，自动连接 农业银行 服务端
     */
    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        log.debug("springboot启动完成，Netty socket 客户端 启动连接 ");
        // 连接 农业银行 服务端
        nettyClient.connect("127.0.0.1", 8101);
        log.debug("Netty socket 客户端 启动完成，已就绪");
    }

}
