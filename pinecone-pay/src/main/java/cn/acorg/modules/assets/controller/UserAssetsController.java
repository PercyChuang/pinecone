package cn.acorg.modules.assets.controller;

import cn.acorg.assets.req.OrderPayMoneyReq;
import cn.acorg.framework.controller.BaseController;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.response.Response;
import cn.acorg.modules.assets.service.UserAssetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户资产 Controller
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 9:27
 */
@RequestMapping("/userAssets")
@RestController
@Slf4j
public class UserAssetsController extends BaseController {

    @Autowired
    private UserAssetsService userAssetsService;

    /**
     * 用户资产支付
     * @param param 请求实体
     * @author 松果
     * @date 2020/11/10 16:12
     * @return cn.acorg.framework.response.Response<java.lang.Boolean>
     */
    @PostMapping("/payMoney")
    public Response<Boolean> payMoney(@RequestBody RequestParam<OrderPayMoneyReq> param) {
        Boolean result = userAssetsService.payMoney(param);
        return buildSuccessResp(result);
    }
}
