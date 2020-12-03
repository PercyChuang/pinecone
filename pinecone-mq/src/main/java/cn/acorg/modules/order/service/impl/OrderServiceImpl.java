package cn.acorg.modules.order.service.impl;

import cn.acorg.framework.enums.pay.PayType;
import cn.acorg.modules.order.entity.OrderDO;
import cn.acorg.modules.order.service.manager.OrderManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.acorg.modules.order.dao.OrderMapper;
import cn.acorg.modules.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 订单实现类
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 15:52
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {


    @Resource
    private OrderManager orderManager;

    /**
     * 用户订单支付
     * @param orderNo 订单编号
     * @author 松果
     * @date 2020/11/9 16:02
     * @return boolean
     */
    @GlobalTransactional(name = "tx-order-orderPay")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean orderPay(String orderNo) {
        log.info("本次分布式事务-全局XID："+ RootContext.getXID());
        // 1. 查询订单信息
        OrderDO orderDO = orderManager.findByOrderNo(orderNo);

        // 2. 订单进行支付
        orderManager.payMoney(orderNo, orderDO.getUserId(), PayType.MY_BANK);

        // 3. 修改订单状态，持久化到数据库
        orderDO.setPayState(1);
        baseMapper.updateById(orderDO);

        if (1 == 1) {
            throw new RuntimeException("主动触发分布式事务");
        }
        return true;
    }
}
