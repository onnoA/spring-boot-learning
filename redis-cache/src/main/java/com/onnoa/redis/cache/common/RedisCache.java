package com.onnoa.redis.cache.common;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/16 15:55
 */
public class RedisCache extends CacheFacade  {
    /** Redis Cache 测试*/
    public static final RedisCache REDIS_TEST_ID = new RedisCache("redis:test:id:");

    protected RedisCache(String keyPrefix) {
        super(keyPrefix);
    }
}
