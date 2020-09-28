package cn.acorg.controller;

import cn.acorg.common.enums.rabbitmq.TestQueueEnum;
import cn.acorg.common.rabbitmq.util.AmqpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author ChenXueSong
 * @date 2020-09-24 21:15
 */
@RequestMapping("test")
@RefreshScope
@RestController
public class TestController {

    @Autowired
    private AmqpClient amqpClient;

    @RequestMapping("test")
    public String test() {
        amqpClient.send(TestQueueEnum.TEST_1.getName(), "This is sender content");
        return "已发送成功";
    }
}
