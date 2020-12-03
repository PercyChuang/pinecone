package cn.acorg.modules.assets.service;

import cn.acorg.assets.entity.UserAssetsDO;
import cn.acorg.assets.req.OrderPayMoneyReq;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.acorg.framework.request.RequestParam;

/**
 * 用户资产 Service 接口
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 9:28
 */
public interface UserAssetsService extends IService<UserAssetsDO> {

    /**
     * 用户订单支付金额
     * @param param 请求实体
     * @author 松果
     * @date 2020/11/10 13:10
     * @return boolean
     */
    boolean payMoney(RequestParam<OrderPayMoneyReq> param);
}
