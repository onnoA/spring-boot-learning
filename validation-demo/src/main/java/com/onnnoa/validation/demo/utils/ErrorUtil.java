package com.onnnoa.validation.demo.utils;

import com.onnnoa.validation.demo.exception.enums.SimpleExceptionEnums;
import com.onnnoa.validation.demo.respone.ResultBean;

/**
 * @Description: 自定义异常信息封装返回工具类
 * @Author: onnoA
 * @Date: 2019/9/21 15:25
 */
public class ErrorUtil {

    /**
     * 功能描述: 自定义业务异常封装返回提
     * @auther: onnoA
     * @date: 2019/9/23 0:10
     */
    public static <T> ResultBean<T> getErrorBean(SimpleExceptionEnums simpleExceptionEnums,String errorParams){
        ResultBean<T> resultBean = new ResultBean<>();
        return resultBean.setCode(simpleExceptionEnums.getCode()).setMsg(SimpleExceptionEnums.getDescWithParam(simpleExceptionEnums,errorParams));
    }
}
