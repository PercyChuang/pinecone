package cn.acorg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author ChenXueSong
 * @date 2020-09-24 20:46
 */
@RequestMapping("test")
@RefreshScope
@RestController
public class TestController {

    @Value("${test.name}")
    private String name;

    @Value("${test.age}")
    private String age;

    @RequestMapping("getConfig")
    public String getConfig() {
        return "这是目前配置：" + name + "," + age;
    }
}
