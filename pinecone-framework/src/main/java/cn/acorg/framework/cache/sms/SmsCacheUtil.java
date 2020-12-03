package cn.acorg.framework.cache.sms;

import cn.acorg.framework.cache.BaseCache;
import cn.acorg.framework.exception.CommonResultEnum;
import cn.acorg.framework.exception.ServiceBaseException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.acorg.framework.enums.sys.SmsTypeEnum;
import cn.acorg.framework.util.SysConstants;
import org.springframework.stereotype.Component;

/**
 * 短信缓存工具类
 * @date 2020/10/29 16:40
 */
@Component
public class SmsCacheUtil extends BaseCache {

    private static final String SMS_KEY = "SMS";

    private static final String SMS_INTERVAL_KEY = "SMS_INTERVAL";
    /**
     * 过期时间 5分钟
     */
    private static final Long EXPIRE_DATE = 60 * 10L;
    /**
     * 发送间隔 1分钟
     */
    private static final Long SENDING_INTERVAL = 60L;


    /**
     * 创建验证码 (长度6位)
     * @date 2020/10/29 16:40
     * @return java.lang.String
     */
    public static String createCode() {
        return RandomUtil.randomNumbers(6);
    }

    /**
     *
     * 创建验证码 (长度4位)
     * @date 2020/10/29 16:41
     * @return java.lang.String
     */
    public static String createCodeFour() {
        return RandomUtil.randomNumbers(4);
    }

    /**
     * 保存验证码
     * @param type 短信类型
     * @param phone 手机号
     * @param code 验证码
     * @date 2020/10/29 16:16
     * @return void
     */
    public static void saveCode(SmsTypeEnum type, String phone, String code) {
        String item = SysConstants.SEPARATION + type.getValue() + SysConstants.SEPARATION + phone;
        // 缓存验证码
        getCache().hset(SMS_KEY, item, code, EXPIRE_DATE);

        // 缓存发送间隔
        getCache().hset(SMS_INTERVAL_KEY, item, code, SENDING_INTERVAL);

    }

    /**
     * 移除-验证码
     * @param type 短信类型
     * @param phone 手机号
     * @date 2020/10/29 16:42
     * @return void 无返回值
     */
    public static void removeCode(SmsTypeEnum type, String phone) {
        String item = type.getValue() + SysConstants.SEPARATION + phone;
        getCache().hdel(SMS_KEY, item);
    }


    /**
     * 获取验证码
     * @param type 短信类型
     * @param phone 手机号
     * @date 2020/10/29 16:43
     * @return java.lang.String
     */
    public static String getCode(SmsTypeEnum type, String phone) {
        String item = type.getValue() + SysConstants.SEPARATION + phone;
        Object code = getCache().hget(SMS_KEY, item);
        if (code == null) {
            return null;
        }
        return code.toString();
    }

    /**
     * 查看是否可以发送验证码
     * @param type 短信类型
     * @param phone 手机号
     * @date 2020/10/29 16:42
     * @return boolean
     */
    public static boolean validBeSent(SmsTypeEnum type, String phone) {
        String item = type.getValue() + SysConstants.SEPARATION + phone;
        Object code = getCache().hget(SMS_INTERVAL_KEY, item);
        if (code == null) {
            return true;
        }
        return false;
    }

    /**
     * 校验-验证码
     * @param type 用户登录的类型-注册登录
     * @param phone 用户手机号
     * @param code 手机验证码
     * @date 2020/10/29 16:39
     * @return void
     */
    public static void checkCode(SmsTypeEnum type, String phone, String code) {
        // 1 获取-验证码（Redis中拿取）
        String sysCode = getCode(type, phone);

        // 2 判断-验证码不存在
        if (StrUtil.isBlank(sysCode)) {
            throw new ServiceBaseException(CommonResultEnum.SMS_CODE_NULL);
        }

        // 3 判断-验证码错误
        if (!sysCode.equals(code)) {
            throw new ServiceBaseException(CommonResultEnum.SMS_CODE_ERROR);
        }

        // 4 验证成功-清除验证码
        removeCode(type, phone);
    }
}
