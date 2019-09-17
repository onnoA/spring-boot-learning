package com.onnoa.httpclient.utils.demo.request;

import com.onnoa.httpclient.utils.demo.utils.HttpClientUtils;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/12 17:43
 */
public class HttpClientTest {
    public static void main(String[] args) {
        String responHtml = HttpClientUtils.doGet("http://www.baidu.com", null);
        System.out.println("百度响应页面：" + responHtml);

    }
}
