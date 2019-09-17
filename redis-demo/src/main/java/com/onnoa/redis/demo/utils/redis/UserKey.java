package com.onnoa.redis.demo.utils.redis;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/4 23:32
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix, int expiredTime) {
        super(prefix, expiredTime);
    }

    public static UserKey userIdWithExpired(int expiredTime) {
        return new UserKey(PrefixConstant.USER + "userId:", expiredTime);
    }

    public static UserKey userVerifyCodeWith(int expiredTime) {
        return new UserKey(PrefixConstant.USER + "verifyCode:", expiredTime);
    }
}
