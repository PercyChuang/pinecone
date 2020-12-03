package cn.acorg.coupon.entity;

import cn.acorg.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 示例类：优惠券
 * @author 松果
 * @version 1.0
 * @date 2020/10/30 13:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("TEST_COUPON_USER_RECORD")
public class CouponUserRecordDO extends BaseDO<CouponUserRecordDO> {
    private static final long serialVersionUID = -5221153026203649532L;

    /**
     * 标题
     */
    private String title;
    /**
     * 抵扣金额
     */
    private BigDecimal deductionAmount;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 是否已删除（0 否 1 是）
     */
    private Integer isDel;

}
