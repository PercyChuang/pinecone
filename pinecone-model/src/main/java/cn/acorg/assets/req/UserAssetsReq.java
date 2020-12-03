package cn.acorg.assets.req;

import cn.acorg.framework.model.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * 用户资产-请求
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 13:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户资产-请求")
public class UserAssetsReq extends BaseReq {

    private static final long serialVersionUID = -1243103858621527345L;
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
