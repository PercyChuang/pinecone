package cn.acorg.common.rabbitmq.config;

import cn.acorg.common.enums.rabbitmq.netty.TestQueueEnum;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq TestConfig
 * @author ChenXueSong
 * @date 2020-09-28 21:43
 */
@Configuration
public class TestConfig {

    @Bean
    public Queue Queue() {
        return new Queue(TestQueueEnum.TEST_1.getName());
    }

}
