package com.onnoa.redis.demo;

import com.onnoa.redis.demo.constant.TbUserRedisConstant;
import com.onnoa.redis.demo.utils.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void redisUtilTest() {
        Integer random = (int) ((Math.random() * 9 + 1) * 100000);
        TbUserRedisConstant keyPrefix = TbUserRedisConstant.tbUserRedisKey(1000);
        redisUtils.set(keyPrefix,"name",random.toString());
    }

}
