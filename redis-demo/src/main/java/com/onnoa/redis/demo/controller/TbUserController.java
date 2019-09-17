package com.onnoa.redis.demo.controller;

import com.onnoa.redis.demo.entity.TbUser;
import com.onnoa.redis.demo.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/5 09:13
 */
@Controller
@RequestMapping(value = "/user/")
public class TbUserController {



    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "redis/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void tbUserRedisTest(@PathVariable("id") Long id) {
        tbUserService.tbUserRedisTest(id);
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    @ResponseBody
    public void tbUserSave(@RequestParam(value = "username", required = false, defaultValue = "默认") String name) {
        System.out.println("进来了，只是测试" + name);
    }
}
