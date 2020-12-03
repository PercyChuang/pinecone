package cn.acorg.framework.exception;

import java.io.Serializable;

/**
 * 异常编码接口
 * @author Z-BL
 * @date 2020年02月07日 14:58:24
 */
public interface ResultCode extends Serializable {
    /**
     * 异常 code
     * @date 2020/11/13 10:39
     * @return java.lang.String
     */
    String getCode();

    /**
     * 异常描述
     * @author 松果
     * @date 2020/11/13 10:42
     * @return java.lang.String
     */
    String getDesc();

}
