package com.onnoa.redis.demo.utils.redislock;

import com.onnoa.redis.demo.utils.redis.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description: redis分布式锁
 *  参考博客：https://yq.aliyun.com/articles/328090
 * @Author: onnoA
 * @Date: 2019/8/4 23:11
 */
@Aspect
@Configuration
public class DistributedLockAspectConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);

    @Resource
    private RedisUtils redisUtils;

    @Pointcut("@annotation(com.onnoa.redis.demo.utils.redislock.RedisLock)")
    private void lockPoint() {

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        boolean lock = redisUtils.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
        if (!lock) {
            LOGGER.info("get lock failed : " + key);
            return null;
        }

        //得到锁,执行方法，释放锁
        LOGGER.info("get lock success : " + key);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            LOGGER.error("execute locked method occured an exception", e);
            throw e;
        } finally {
            boolean releaseResult = redisUtils.releaseLock(key);
            LOGGER.info("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
    }
}
