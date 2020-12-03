package cn.acorg.framework.enums.pay;

import cn.acorg.framework.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型枚举
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 13:42
 */
@Getter
@AllArgsConstructor
public enum PayType implements BaseEnum {

    /**
     * 网商银行
     */
    MY_BANK(0, "网商银行"),
    /**
     * 平安银行
     */
    PING_AN_BANK(1, "平安银行"),
    /**
     * 支付宝
     */
    ALI_PAY(2, "支付宝");

    private final int value;

    private final String desc;
}
