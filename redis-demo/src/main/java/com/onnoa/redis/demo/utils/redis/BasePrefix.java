package com.onnoa.redis.demo.utils.redis;

/**
 * @Description: redis 基础类
 * @Author: onnoA
 * @Date: 2019/8/4 23:30
 */
public class BasePrefix implements KeyPrefix {
    private int expiredTime;
    private String prefix;

    public BasePrefix(String prefix, int expiredTime) {
        this.expiredTime = expiredTime;
        this.prefix = prefix;
    }

    // 0表示永不过期
    public BasePrefix(String prefix) {
        this(prefix, 0);
    }

    @Override
    public int expiredTime() {
        return expiredTime;
    }

    @Override
    public String prefix() {
        return this.prefix;
    }

}
