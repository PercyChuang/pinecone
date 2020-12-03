package cn.acorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PineconePayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineconePayApplication.class, args);
    }

}
