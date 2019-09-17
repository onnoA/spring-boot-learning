package com.onnoa.alipay.demo.controller;

import com.onnoa.alipay.demo.service.AlipayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Description: 支付宝沙箱支付
 * @Author: onnoA
 * @Date: 2019/8/16 14:21
 */
@Controller
public class AliPayTestController {
    @Resource
    AlipayService alipayService;
    /*@Value("${alipay.notifyUrl}")
    private String url;*/

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(HttpServletResponse response){
        try {
            if(response.getHeaderNames() == null){
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().write("<h1>支付测试</h1>");
                response.getWriter().write("<br/><a href='paytest'>前往支付页面</a>");
                response.getWriter().close();
            }

            else{
                AliPayTestController.setProper("alipay.notifyUrl","www.baidu.com");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/paytest",method = RequestMethod.GET)
    public void testPay(HttpServletResponse response){
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write(alipayService.getwayPay());
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public void callback(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setContentType("text/html; charset=utf-8");
            Enumeration enu = request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                response.getWriter().write(paraName+": "+request.getParameter(paraName)+"<br>");
            }
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/callback_nsync")
    public void callback_nsync(HttpServletRequest request, HttpServletResponse response){
        System.out.println("1111111111111111111111111111");
        try {
            response.setContentType("text/html; charset=utf-8");
            Enumeration enu = request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                System.out.println(paraName+": "+request.getParameter(paraName));
            }
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setProper(String Key, Object value) {
        Properties pro = new Properties();
        FileInputStream fis = null;
        String path = AliPayTestController.class.getClassLoader().getResource("application.properties").getFile();
        // 路径中有空格，路径会将空格自动转为 %20，所以把他替换为了空格
        //path = path.replace("%20", " ");
        File file = new File(path);
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            pro.load(bis);
            FileOutputStream fos = new FileOutputStream(file);
            pro.setProperty(Key, String.valueOf(value));
            pro.store(fos, null);
            System.out.println(pro.getProperty("alipay.notifyUrl"));
            System.out.println(pro);
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
