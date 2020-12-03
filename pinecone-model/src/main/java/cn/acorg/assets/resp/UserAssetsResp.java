package cn.acorg.assets.resp;

import cn.acorg.framework.model.BaseResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * 用户资产-返回
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 13:19
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户资产-返回")
public class UserAssetsResp extends BaseResp {

    private static final long serialVersionUID = -9189712621422097799L;
    /**
     * id 主键
     */
    @ApiModelProperty("id 主键")
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 余额
     */
    @ApiModelProperty("余额")
    private BigDecimal balance;

}
