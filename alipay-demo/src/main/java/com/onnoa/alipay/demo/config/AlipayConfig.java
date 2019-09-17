package com.onnoa.alipay.demo.config;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 支付宝沙箱支付配置类
 * @Author: onnoA
 * @Date: 2019/8/16 14:18
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfig {

    @Resource
    private AlipayProperties properties;

    /**
     * alipay-sdk-java
     * @return
     */
    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(properties.getGatewayUrl(),
                properties.getAppid(),
                properties.getPrivateKey(),
                properties.getFormate(),
                properties.getCharset(),
                properties.getAlipayPublicKey(),
                properties.getSignType());
    }
}
