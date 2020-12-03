package cn.acorg.coupon.resp;

import cn.acorg.framework.model.BaseResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * 优惠券-返回
 * @author 松果
 * @version 1.0
 * @date 2020/10/30 13:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "优惠券-返回")
public class CouponUserRecordResp extends BaseResp {

    private static final long serialVersionUID = -642526553984523567L;

    /**
     * id 主键
     */
    @ApiModelProperty("id 主键")
    private Long id;
    /**
     * 优惠券标题不能为空
     */
    @ApiModelProperty("优惠券标题")
    private String title;
    /**
     * 抵扣金额
     */
    @ApiModelProperty("抵扣金额")
    private BigDecimal deductionAmount;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

}
