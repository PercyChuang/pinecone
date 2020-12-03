package cn.acorg.modules.order.service.manager;

import cn.acorg.assets.req.OrderPayMoneyReq;
import cn.acorg.client.pay.PayClient;
import cn.acorg.framework.enums.pay.PayType;
import cn.acorg.modules.order.entity.OrderDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.acorg.framework.exception.CommonResultEnum;
import cn.acorg.framework.exception.ServiceBaseException;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.response.Response;
import cn.acorg.modules.order.dao.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单 Manager
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 16:20
 */
@Service
public class OrderManager {

    /**
     * 订单 Mapper
     */
    @Resource
    private OrderMapper orderMapper;
    /**
     * 支付服务客户端
     */
    @Resource
    private PayClient payClient;

    /**
     * 根据订单编号查询
     * @param orderNo 订单编号
     * @author 松果
     * @date 2020/11/10 16:33
     * @return cn.acorg.modules.order.entity.OrderDO
     */
    public OrderDO findByOrderNo(String orderNo) {
        QueryWrapper<OrderDO> wrapper = Wrappers.query();
        wrapper.lambda()
                .eq(OrderDO::getOrderNo, orderNo);

        List<OrderDO> list = orderMapper.selectList(wrapper);
        return list.size() == 0 ? null : list.get(0);
    }


    /**
     * 封装参数，调用支付服务
     * @param orderNo 订单编号
     * @param userId 用户ID
     * @param payType 支付方式
     * @author 松果
     * @date 2020/11/10 16:17
     * @return void
     */
    public void payMoney(String orderNo, Long userId, PayType payType) {
        // 1. 构造订单支付请求实体
        RequestParam<OrderPayMoneyReq> requestParam = new RequestParam<>();
        OrderPayMoneyReq orderPayMoneyReq = OrderPayMoneyReq.builder()
                .orderNo(orderNo)
                .payType(payType.getValue())
                .userId(userId).build();
        requestParam.setData(orderPayMoneyReq);

        // 2. 调用支付服务支付接口
        Response<Boolean> response = payClient.payMoney(requestParam);

        // 3. 判断是否执行成功，不成功主动触发分布式事务进行回滚
        if (!response.getCode().equals(CommonResultEnum.SUCCESS.getCode())) {
            // 接口调用失败，抛出异常进行回滚
            throw new ServiceBaseException(response.getMessage());
        }
    }
}
