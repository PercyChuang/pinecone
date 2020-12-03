package cn.acorg.modules.order.req;

import cn.acorg.framework.model.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单-请求
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 13:22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "订单-请求")
@EqualsAndHashCode(callSuper = true)
public class OrderReq extends BaseReq {

    private static final long serialVersionUID = -6848189874572392179L;

    /**
     * ID 主键
     */
    @ApiModelProperty("id 主键")
    private Long id;
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String orderNo;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;
    /**
     * 实付金额
     */
    @ApiModelProperty("实付金额")
    private BigDecimal actualMoney;
    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal totalMoney;
    /**
     * 支付状态 0 未支付 1 已支付 2 已退款
     */
    @ApiModelProperty("支付状态 0 未支付 1 已支付 2 已退款")
    private Integer payState;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateDate;
}
