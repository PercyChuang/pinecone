package cn.acorg.modules.order.controller;

import cn.acorg.framework.controller.BaseController;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.response.Response;
import cn.acorg.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单 Controller
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 15:46
 */
@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单支付
     * @param requestParam 请求实体
     * @author 松果
     * @date 2020/11/10 17:10
     * @return cn.acorg.framework.response.Response<java.lang.String>
     */
    @PostMapping("/orderPay")
    public Response<Boolean> orderPay(@RequestBody RequestParam<String> requestParam) {
        Boolean result = orderService.orderPay(requestParam.getData());
        return buildSuccessResp(result);
    }

}
