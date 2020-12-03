package cn.acorg.modules.assets.service.impl;

import cn.acorg.assets.entity.UserAssetsDO;
import cn.acorg.assets.req.OrderPayMoneyReq;
import cn.acorg.modules.assets.service.manger.UserAssetsManger;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.validator.ValidatorUtils;
import cn.acorg.framework.validator.group.DefaultGroup;
import cn.acorg.modules.assets.service.UserAssetsService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 用户资产 Service 实现类
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 9:29
 */
@Slf4j
@Service
public class UserAssetsServiceImpl extends ServiceImpl<UserAssetsMapper, UserAssetsDO> implements UserAssetsService{

    @Resource
    private UserAssetsManger userAssetsManger;

    /**
     * 用户订单支付金额
     * @param param 请求实体
     * @author 松果
     * @date 2020/11/10 13:10
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean payMoney(RequestParam<OrderPayMoneyReq> param) {
        log.info("本次分布式事务-全局XID："+ RootContext.getXID());
        // 1. 检验请求参数
        OrderPayMoneyReq req = param.getData();
        ValidatorUtils.validateEntity(req, DefaultGroup.class);

        // 2.获取订单信息 OrderResp order = orderClient.getOrder(req.getOrderNo())
        // 3.获取订单所属用户资产
        UserAssetsDO userAssetsDO = userAssetsManger.findByUserId(req.getUserId());

        // 4. 判断剩余资产订单金额，通过扣除资产 userAssetsDO.getBalance() > order.getActualMoney()
        userAssetsDO.setBalance(userAssetsDO.getBalance().subtract(new BigDecimal("100")));

        return baseMapper.updateById(userAssetsDO) > 0;
    }
}
