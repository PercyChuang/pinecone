package cn.acorg.common.rabbitmq.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitMQ 消息发送工具类
 * @author ChenXueSong
 * @date 2020-09-25 22:24
 */
@Slf4j
@Component
public class AmqpClient {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 直连模式，发送消息
     * @param queueKey 队列名称
     * @param content 发送内容
     */
    public void send(String queueKey, Object content){
        log.debug("队列名称：{}, 发送内容：{}", queueKey, content);
        this.rabbitmqTemplate.convertAndSend(queueKey, content);
    }

}