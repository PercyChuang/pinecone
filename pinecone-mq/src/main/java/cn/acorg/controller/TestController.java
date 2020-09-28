package cn.acorg.controller;

import cn.acorg.common.enums.rabbitmq.TestQueueEnum;
import cn.acorg.common.enums.rabbitmq.TestQueueHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author ChenXueSong
 * @date 2020-09-24 20:46
 */
@RequestMapping("test")
@RefreshScope
@RestController
@Slf4j
public class TestController {


    @RabbitListener(queues = {TestQueueHandler.TEST_1})
    @RabbitHandler
    public void process(String content) {
        log.debug("收到的内容：{}", content);
    }

}
