package com.onnoa.redis.demo.utils.redis;

/**
 * @Description: redis 自定义key 前缀规则
 * @Author: onnoA
 * @Date: 2019/8/4 23:31
 */
public class PrefixConstant {
    /**
     * 项目redis key前缀
     */
    public static final String ONNOA = "onnoa:";

    /**
     * 服务【用户】redis key前缀
     */
    public static final String USER = ONNOA + "tbuser:";

    /**
     * 服务【活动】redis key前缀
     */
    public static final String ACTIVITY = ONNOA + "activity:";

    /**
     * 服务【消息】redis key前缀
     */
    public static final String MESSAGE = ONNOA + "message:";

    /**
     * 服务【分时预订】redis key前缀
     */
    public static final String TIMESHARE = ONNOA + "timeshare:";
}
