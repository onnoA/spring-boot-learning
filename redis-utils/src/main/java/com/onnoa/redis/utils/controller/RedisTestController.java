package com.onnoa.redis.utils.controller;

import com.onnoa.redis.utils.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/17 10:08
 */
@Controller
@RequestMapping(value = "/user/")
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "save",method = RequestMethod.GET)
    @ResponseBody
    public void save(@RequestParam(value="username",required=false,defaultValue="默认") String name){
        System.out.println("名字是："+name);
        redisUtil.set(name,"值");
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public void save1(@RequestParam(value="name",required=false,defaultValue="默认") String name){
       TbUserCache.PAY_REQUEST_NO.set(name,"名字");
    }
}
