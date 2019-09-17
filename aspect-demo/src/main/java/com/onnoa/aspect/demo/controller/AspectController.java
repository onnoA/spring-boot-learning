package com.onnoa.aspect.demo.controller;

import com.onnoa.aspect.demo.LongOu;
import com.onnoa.aspect.demo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: AspectController 切面controller
 * @Author: onnoA
 * @Date: 2019/7/28 11:11
 */
@RestController  //springboot注解，类下的所有方法返回的数据类型为Jason
public class AspectController {
    @Value("${ali.pay.gateway}")
    private String url;

    public static void main(String[] args) {

        List<String> zkList = new ArrayList<>();
        zkList.add("//172.16.0.49");
        zkList.add("//10.10.67.62");
        zkList.add("//10.46.67.32");
        if(zkList != null){}
        String substring = "http://10.10.67.62:8080".substring(5, 18);
        System.out.println(substring);
        System.out.println(zkList.contains(substring));
    }

    @RequestMapping("/getUser")
    @LongOu(module = "http://www.cnblogs.com/lingyejun/")//这是我们自定义的注解，加上这个注解后就能够切到这个方法了。
    public User getUserTest(/*@PathVariable Integer id,*/ HttpServletRequest request) throws Exception  {
        System.out.println("方法中。。。。。");
        System.out.println(url);
        User user=new User();
        user.setName("小明");
        user.setPassword("xxxx");

        return user;
    }
}
