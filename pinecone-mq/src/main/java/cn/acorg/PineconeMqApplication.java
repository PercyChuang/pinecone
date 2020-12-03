package cn.acorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消息中心
 * @author ChenXueSong
 */
@EnableFeignClients
@SpringBootApplication
public class PineconeMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineconeMqApplication.class, args);
    }

}
