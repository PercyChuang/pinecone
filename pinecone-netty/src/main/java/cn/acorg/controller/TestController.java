package cn.acorg.controller;

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


    @RequestMapping("test")
    public String test() {
        List<String> list = new ArrayList<>();
        AbstractList<String> list2 = new ArrayList<>();
        return "";
    }
}
