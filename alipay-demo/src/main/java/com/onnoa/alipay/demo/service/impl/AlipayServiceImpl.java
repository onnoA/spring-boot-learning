package com.onnoa.alipay.demo.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.onnoa.alipay.demo.config.AlipayProperties;
import com.onnoa.alipay.demo.service.AlipayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/16 14:20
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayProperties aliPayProperties;

    @Override
    public String getwayPay() {
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("支付测试");
        model.setTotalAmount("10");
        model.setBody("支付测试，共10元");
        model.setProductCode(productCode);

        AlipayTradePagePayRequest pagePayRequest = new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
        pagePayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
        pagePayRequest.setBizModel(model);

        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String retStr = null;
        try {
            retStr = alipayClient.pageExecute(pagePayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return retStr;
    }

}
