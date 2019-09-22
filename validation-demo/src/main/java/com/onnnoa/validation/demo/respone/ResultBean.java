package com.onnnoa.validation.demo.respone;

import java.io.Serializable;

/**
 * @Description: 返回提封装工具类
 * @Author: onnoA
 * @Date: 2019/9/21 15:30
 */
public class ResultBean<T> implements Serializable {

    private static final int SUCCESS = 200;
    private static final int FAIL = 100;
    private static int code = SUCCESS;
    private static String msg = "success";
    private T date;

    public static <T> ResultBean<T> success(T date){
        ResultBean<T> resultBean = new ResultBean<>();
        return resultBean.setDate(date).setCode(SUCCESS).setMsg(msg);
    }

    public static <T> ResultBean<T> success(){
        ResultBean<T> resultBean = new ResultBean<>();
        return resultBean.setCode(SUCCESS).setMsg(msg);
    }

    public static <T> ResultBean<T> fail(String message){
        ResultBean<T> resultBean = new ResultBean<>();
        return resultBean.setCode(FAIL).setMsg(message);
    }

    public static <T> ResultBean<T> error(int code,String message){
        ResultBean<T> resultBean = new ResultBean<>();
        return resultBean.setCode(code).setMsg(message);
    }


    public int getCode() {
        return code;
    }

    public ResultBean<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBean<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getDate() {
        return date;
    }

    public ResultBean<T> setDate(T date) {
        this.date = date;
        return this;
    }
}
