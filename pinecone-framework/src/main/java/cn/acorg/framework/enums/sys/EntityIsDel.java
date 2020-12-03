package cn.acorg.framework.enums.sys;

import cn.acorg.framework.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 实体删除状态
 * @author 松果
 * @date 2020/10/29 17:01
 */
@Getter
@AllArgsConstructor
public enum EntityIsDel implements BaseEnum {
    /**
     * 未删除
     */
    NOT(0, "未删除"),
    /**
     * 已删除
     */
    YES(1, "已删除");

    private final int value;

    private final String desc;
}
