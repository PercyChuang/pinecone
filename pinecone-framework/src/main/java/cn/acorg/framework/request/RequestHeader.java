package cn.acorg.framework.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求实体头
 * @author 松果
 * @date 2020/10/29 16:20
 */
@Data
public class RequestHeader implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * token
     */
    private String token;
    /**
     * 签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 手机序列号
     */
    private String imei;
    /**
     * 请求ip
     */
    private String ip;

}
