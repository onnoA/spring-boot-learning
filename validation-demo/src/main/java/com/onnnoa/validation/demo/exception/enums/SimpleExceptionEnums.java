package com.onnnoa.validation.demo.exception.enums;


/**
 * @Description: 自定义异常枚举
 * @Author: onnoA
 * @Date: 2019/9/21 08:29
 */
public enum  SimpleExceptionEnums {

    INPUT_PARAMS_ERROR(10010,"参数校验失败,请重新输入");

    private Integer code;
    private String errorMsg;



    SimpleExceptionEnums(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static String getDescWithParam(SimpleExceptionEnums simpleExceptionEnums,String param){
        String errorString = simpleExceptionEnums.getErrorMsg();
        return String.format(errorString,param);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
