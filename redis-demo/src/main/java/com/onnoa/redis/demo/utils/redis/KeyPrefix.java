package com.onnoa.redis.demo.utils.redis;

/**
 * @Description: key 前缀接口
 * @Author: onnoA
 * @Date: 2019/8/4 23:19
 */
public interface KeyPrefix {

    public int expiredTime();

    public String prefix();
}
