package cn.acorg.redis;

import cn.acorg.PineconeMqApplication;
import cn.acorg.framework.redis.RedisUtil;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author 松果
 * @version 1.0
 * @date 2020/11/3 16:04
 */
@SpringBootTest(classes = PineconeMqApplication.class)
@RunWith(value = SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        redisUtil.set("chenxuesong", 21);
        String value =  redisUtil.get("chenxuesong").toString();

        Map<String, Object> map = Maps.newHashMap();
        map.put("松果", 21);
        map.put("鼹鼠", 21);
        map.put("逍遥", 27);

        boolean result = redisUtil.hmset("userGroup", map);

        Map<Object, Object> resultMap = redisUtil.hmget("userGroup");
        System.out.println(resultMap);

    }
}
