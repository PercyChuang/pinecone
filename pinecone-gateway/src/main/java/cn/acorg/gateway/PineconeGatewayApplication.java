package cn.acorg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关服务启动类
 * @author 松果
 * @date 2020/11/2 15:07
 * @return
 */
@EnableFeignClients
@SpringBootApplication
public class PineconeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineconeGatewayApplication.class, args);
    }

}
