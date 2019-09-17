package com.onnoa.redis.demo.constant;

import com.onnoa.redis.demo.utils.redis.BasePrefix;
import com.onnoa.redis.demo.utils.redis.PrefixConstant;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/5 00:29
 */
public class TbUserRedisConstant extends BasePrefix {

    // 默认超时时间
    private static int DefaultTimeoutSenonds = 1 * 24 * 60 * 60; // 1 天

    public TbUserRedisConstant(String prefix, int expiredTime) {
        super(prefix, expiredTime);
    }

    public TbUserRedisConstant(String prefix) {
        super(prefix);
    }

    public static TbUserRedisConstant tbUserRedisKey(int expiredTime) {
        return new TbUserRedisConstant(PrefixConstant.USER + "redisTest:", expiredTime);
    }
}
