package com.onnoa.redis.cache.common.impl;

import com.onnoa.redis.cache.common.KVOperate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis K、V操作实现类
 * @Author: onnoA
 * @Date: 2019/9/10 10:37
 */
@Component
public class RedisKvOperate implements KVOperate {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.redis.host}")
    private String clusterOn;

    public RedisKvOperate(){

    }

    /**
     * 功能描述: 根据配置文件的集群开关，判断是否使用集群
     *
     * @return:
     * @auther: onnoA
     * @date: 2019/9/10 10:38
     */
    /*@SuppressWarnings("unchecked")
    public RedisKvOperate(String clusterOn) {
        if (clusterOn != null && clusterOn.equalsIgnoreCase("off")) {
            redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
        } else {
            redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisClusterTemplate");
        }
    }*/
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, int timeoutSecond) {
        redisTemplate.opsForValue().set(key, value, timeoutSecond, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String key, int timeoutSecond) {
        redisTemplate.expire(key, timeoutSecond, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public long inc(String key) {
        return incBy(key, 1);
    }

    @Override
    public long incBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 功能描述: 根据前缀删除所有key
     *
     * @auther: onnoA
     * @date: 2019/9/10 10:52
     */
    @Override
    public void delKeysByPrefix(String keyPrefix) {
        Set<String> keys = keys(keyPrefix);
        for (String key : keys) {
            del(key);
        }
    }

    /**
     * 功能描述: 根据keys获取所有的value,返回的顺序和keys一致
     *
     * @auther: onnoA
     * @date: 2019/9/10 10:52
     */
    @Override
    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public long lpush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object lpop(String key) {
        return key == null ? null : redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public List<Object> range(String key, long start, long end) {
        return key == null ? null : redisTemplate.opsForList().range(key, start, end);
    }

    // ******************************** hash 操作 *************************************************

    @Override
    public boolean hhasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Object hget(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Set<Object> hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Map<Object, Object> hentries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void hput(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public long hdelete(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public Object rpop(String key) {
        return key == null ? null : redisTemplate.opsForList().rightPop(key);
    }


    // ******************************** hash 操作 *************************************************
}
