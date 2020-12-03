package cn.acorg.assets.entity;

import cn.acorg.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 示例类：用户资产
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 10:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("TEST_USER_ASSETS")
public class UserAssetsDO extends BaseDO<UserAssetsDO> {
    private static final long serialVersionUID = -2692994849819621723L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 是否已删除（0 否 1 是）
     */
    private Integer isDel;
    /**
     * 版本号
     */
    private Integer version;



}
