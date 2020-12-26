package com.biocv.boot.web;

/**
 * @author kai
 * @date 2020/10/17 11:08
 */
public enum ResultCode {

    /** 成功 */
    SUCCESS(200, "success"),
    /** 权限不足 */
    FORBIDDEN(403,"Sorry,you don't have the authority to access this resource"),
    /** 认证失败,携带了认证信息 */
    AUTHENTICATION_FAIL(401,"Authenticate fail"),
    /** 没有携带认证参数去访问资源 */
//    AUTHENTICATION_NULL(403,"The "),
    /** 参数无效 */
    PARAM_IS_INVALID(1001, "Parameter is invalid");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }


}
