package cn.acorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PineconeUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineconeUserApplication.class, args);
    }

}
