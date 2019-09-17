package com.onnoa.redis.demo;

import com.onnoa.redis.demo.utils.redis.RedisUtils;
import com.onnoa.redis.demo.utils.redislock.DistributedLockAspectConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.onnoa.redis.demo.mapper")
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    /*@Bean
    public RedisUtils redisUtils() {
        return new RedisUtils();
    }
       */

    //redis分布式锁
    @Bean
    public DistributedLockAspectConfiguration distributedLockAspectConfiguration() {
        return new DistributedLockAspectConfiguration();
    }

}
