package com.biocv.boot.autoconfigure.web;

/**
 * 自定义异常
 *
 * @author kai
 * @date 2020/12/23 22:43
 */
public class CustomErrorType {

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomErrorType() {
    }
}
