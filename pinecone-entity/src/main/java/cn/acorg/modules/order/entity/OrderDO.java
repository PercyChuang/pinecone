package cn.acorg.modules.order.entity;

import cn.acorg.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 示例类：订单
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 10:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TEST_ORDER")
public class OrderDO extends BaseDO<OrderDO> {
    private static final long serialVersionUID = -5200696731170371404L;

    /**
     * 编号
     */
    private String orderNo;
    /**
     * 标题
     */
    private String title;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 实付金额
     */
    private BigDecimal actualMoney;
    /**
     * 总金额
     */
    private BigDecimal totalMoney;
    /**
     * 支付状态 0 未支付 1 已支付 2 已退款
     */
    private Integer payState;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 是否已删除（0 否 1 是）
     */
    private Integer isDel;
}
