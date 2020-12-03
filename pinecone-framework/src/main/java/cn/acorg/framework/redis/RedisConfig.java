package cn.acorg.framework.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类 @EnableCaching 开启缓存注解
 * @author Z-BL
 * @date 2020年02月10日 15:35:17
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * retemplate相关配置
     * @param factory 1
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);
        jacksonSeial.setObjectMapper(om);


        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 值采用 json 序列化
        template.setValueSerializer(jacksonSeial);

        // 设置 hash key 和 value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 对 Hash 类型 数据操作
     * @param redisTemplate redis模板工具类
     * @author 松果
     * @date 2020/10/29 15:16
     * @return org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,java.lang.Object>
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对 String 类型 数据操作
     * @param redisTemplate redis模板工具类
     * @author 松果
     * @date 2020/10/29 15:16
     * @return org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.Object>
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对 List 类型的数据操作
     * @param redisTemplate redis模板工具类
     * @author 松果
     * @date 2020/10/29 15:15
     * @return org.springframework.data.redis.core.ListOperations<java.lang.String,java.lang.Object>
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     * @param redisTemplate redis模板工具类
     * @author 松果
     * @date 2020/10/29 15:14
     * @return org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.Object>
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     * @param redisTemplate redis模板工具类
     * @author 松果
     * @date 2020/10/29 15:16
     * @return org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.Object>
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
