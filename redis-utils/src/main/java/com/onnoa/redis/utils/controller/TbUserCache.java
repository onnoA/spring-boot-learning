package com.onnoa.redis.utils.controller;

import com.onnoa.redis.utils.utils.CacheFacade;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/17 10:46
 */
public class TbUserCache extends CacheFacade {

    public static final TbUserCache PAY_REQUEST_NO = new TbUserCache("tbuser:user:");

    protected TbUserCache(String keyPrefix) {
        super(keyPrefix);
    }
}
