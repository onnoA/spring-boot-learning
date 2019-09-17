package com.onnoa.redis.cache.controller;

import com.onnoa.redis.cache.common.RedisCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/16 15:52
 */
@Controller
public class RedisController {

    @GetMapping(value = "redis/test")
    @ResponseBody
    public void redis(@RequestParam(value="name",required=false,defaultValue="默认") String name){
        System.out.println(name);
        RedisCache.REDIS_TEST_ID.set(name,"这只是一个redis测试");
    }
}
