package com.onnoa.pay.config.impl;

import com.onnoa.pay.config.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/6 17:38
 */
public class MyConfig implements WXPayConfig {
    public static String service="http://10.60.7.92/AppServer/WxCallbackServlet";//支付完成后的异步回调
    private byte[] certData;
    public MyConfig() throws Exception {
        String certPath = "C:/......../apiclient_cert.p12";//从微信商户平台下载的安全证书存放的目录
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }
    public String getAppID() {
        return "wx1234567890987";//如初appid
    }

    public String getMchID() {
        return "12345678";//商户号
    }

    public String getKey() {
        return "I1234556789uyertgerger54";//密钥
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}

