package com.onnnoa.validation.demo.exception;

/**
 * @Description: 自定义异常类
 * @Author: onnoA
 * @Date: 2019/9/21 08:11
 */
public class SimpleException extends RuntimeException {

    private int code;
    private String msg;

    public SimpleException(){

    }

    public SimpleException (int code,String msg){
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
