package cn.acorg.modules.order.service;

import cn.acorg.modules.order.entity.OrderDO;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 订单接口
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 15:52
 */
public interface OrderService extends IService<OrderDO> {

    /**
     * 用户订单支付
     * @param orderNo 订单编号
     * @author 松果
     * @date 2020/11/9 16:02
     * @return boolean
     */
    boolean orderPay(String orderNo);
}
