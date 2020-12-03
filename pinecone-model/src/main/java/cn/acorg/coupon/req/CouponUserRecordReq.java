package cn.acorg.coupon.req;

import cn.acorg.framework.model.BaseReq;
import cn.acorg.framework.validator.group.AddGroup;
import cn.acorg.framework.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 优惠券-请求
 * @author 松果
 * @version 1.0
 * @date 2020/10/30 13:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "优惠券-请求")
public class CouponUserRecordReq extends BaseReq {

    private static final long serialVersionUID = -615361933387340759L;

    /**
     * id 主键
     */
    @NotNull(message = "id 主键不能为 null", groups = UpdateGroup.class)
    @ApiModelProperty("id 主键")
    private Long id;
    /**
     * 优惠券标题不能为空
     */
    @NotBlank(message = "优惠券标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty("优惠券标题")
    private String title;
    /**
     * 抵扣金额
     */
    @NotNull(message = "抵扣金额不能为 null", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty("抵扣金额")
    private BigDecimal deductionAmount;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为 null", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty("用户ID")
    private Long userId;
}
