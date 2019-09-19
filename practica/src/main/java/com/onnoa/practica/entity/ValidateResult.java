package com.onnoa.practica.entity;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/19 13:47
 */
public class ValidateResult {
    private boolean isValid = true;
    private String message;

    public ValidateResult() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.isValid = false;
        this.message = message;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
