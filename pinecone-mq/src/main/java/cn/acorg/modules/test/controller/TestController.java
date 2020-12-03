package cn.acorg.modules.test.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import cn.acorg.client.netty.NettyClient;
import cn.acorg.common.enums.rabbitmq.netty.TestQueueHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author ChenXueSong
 * @date 2020-09-24 20:46
 */
@RequestMapping("test")
@RestController
@Slf4j
public class TestController {

    @Resource
    private NettyClient nettyClient;

    @RabbitListener(queues = {TestQueueHandler.TEST_1})
    @RabbitHandler
    public void process(String content) {
        log.debug("收到的内容：{}", content);
    }


    @RequestMapping("testFeign")
    public String testFeign() {
        List<String> list = new ArrayList<>();
        list.add("松果");
        list.add("鼹鼠");
        list.add("无忌");
        String result = nettyClient.test2(list);
        return "这是调用 nettyClient.test2 返回的内容：" + result;
    }

    public String testFeignFallback(long s, BlockException ex) {
        ex.printStackTrace();
        return "请勿频繁访问";
    }

}
