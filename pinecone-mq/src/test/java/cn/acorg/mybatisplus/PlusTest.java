package cn.acorg.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.acorg.PineconeMqApplication;
import cn.acorg.modules.order.dao.OrderMapper;
import cn.acorg.modules.order.entity.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * MybatisPlus 与 Seata自动代理数据源兼容测试
 * @author 松果
 * @version 1.0
 * @date 2020/11/11 9:19
 */
@Slf4j
@SpringBootTest(classes = PineconeMqApplication.class)
@RunWith(value = SpringRunner.class)
public class PlusTest {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void test() {
        List<OrderDO> list = orderMapper.selectList(Wrappers.query());
        orderMapper.deleteById(list.get(0).getId());
        log.info(JSON.toJSONString(list));
    }
}
