package com.onnoa.pay.config;

import java.io.InputStream;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/6 17:37
 */
public interface WXPayConfig {

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public String getAppID();


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public String getMchID();


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public String getKey();


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    public InputStream getCertStream();

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs();

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs();


}
