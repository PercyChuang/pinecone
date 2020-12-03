package cn.acorg.framework.cache.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * token信息
 * @author Z-BL
 * @date 2020年02月11日 11:28:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = -4644878191790408029L;

    /**
     * token
     */
    private String token;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 税源地
     */
    private String taxId;
}
