package cn.acorg.framework.cache;


import cn.acorg.framework.context.ApplicationContextProvider;
import cn.acorg.framework.redis.RedisUtil;

/**
 * 缓存父类
 * @author 松果
 * @date 2020/2/11 11:26
 */
public class BaseCache {

    /**
     * description: 获取缓存
     * @author 松果
     * @date 2020/2/11 11:26
     * @return cn.acorg.framework.redis.RedisUtil
     */
    protected static RedisUtil getCache(){
        return ApplicationContextProvider.getBean(RedisUtil.class);
    }
}
