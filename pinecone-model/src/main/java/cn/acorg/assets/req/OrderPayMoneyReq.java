package cn.acorg.assets.req;

import cn.acorg.framework.model.BaseReq;
import cn.acorg.framework.validator.group.DefaultGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户订单支付扣款-请求
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 13:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户订单支付扣款-请求")
public class OrderPayMoneyReq extends BaseReq {

    private static final long serialVersionUID = -6359137023475518701L;

    @NotBlank(message = "订单编号不能为空", groups = DefaultGroup.class)
    @ApiModelProperty("订单编号")
    private String orderNo;

    @NotNull(message = "支付方式不能为 null", groups = DefaultGroup.class)
    @Range(message = "0：网商银行 1：平安银行 2：支付宝", min = 0, max = 2, groups = DefaultGroup.class)
    @ApiModelProperty("0 否 1 是")
    private Integer payType;


    private Long userId;

}
