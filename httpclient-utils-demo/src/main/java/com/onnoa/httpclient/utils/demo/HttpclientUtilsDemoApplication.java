package com.onnoa.httpclient.utils.demo;

import com.onnoa.httpclient.utils.demo.utils.HttpClientUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HttpclientUtilsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpclientUtilsDemoApplication.class, args);
        String responHtml = HttpClientUtils.doGet("http://www.baidu.com", null);
        System.out.println("百度响应页面：" + responHtml);
    }

}
