package cn.acorg.controller;

import com.alibaba.fastjson.JSON;
import cn.acorg.common.enums.rabbitmq.netty.TestQueueEnum;
import cn.acorg.common.rabbitmq.util.AmqpClient;
import cn.acorg.coupon.req.CouponUserRecordReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
public class TestController {

    @Autowired
    private AmqpClient amqpClient;

    @RequestMapping("test")
    public String test() {
        amqpClient.send(TestQueueEnum.TEST_1.getName(), "This is sender content");
        return "已发送成功";
    }

    @PostMapping("test2")
    public String test2(@RequestBody List<String> list, CouponUserRecordReq req) {
        log.debug("接受自 Mq 服务的请求实体：{}", JSON.toJSONString(list));
        if (1 == 1) {
            throw new RuntimeException("测试制造异常");
        }
        return "调用成功";
    }

}
