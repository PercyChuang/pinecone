package cn.acorg.client.pay;

import cn.acorg.assets.req.OrderPayMoneyReq;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 支付服务客户端
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 15:58
 */
@FeignClient(name = "pinecone-pay")
public interface PayClient {


    /**
     * 用户资产支付
     * @param param 请求实体
     * @author 松果
     * @date 2020/11/10 16:12
     * @return cn.acorg.framework.response.Response<java.lang.Boolean>
     */
    @PostMapping("/userAssets/payMoney")
    Response<Boolean> payMoney(RequestParam<OrderPayMoneyReq> param);
}
