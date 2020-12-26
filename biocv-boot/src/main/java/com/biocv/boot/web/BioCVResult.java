package com.biocv.boot.web;

import lombok.Data;

import java.io.Serializable;

/**
 * Rest响应
 * @author kai
 * @date 2020/10/17 11:14
 */
@Data
public class BioCVResult implements Serializable {

    private Integer code;

    private String message;

    private Object data;


    private BioCVResult(){

    }

    public BioCVResult(ResultCode resultCode, Object data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 成功响应
     *
     * @param data
     * @return com.biocv.base.vo.Result
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-17 11:21
     * @since 1.0.0
    */
    public static BioCVResult success(Object data){
        return new BioCVResult(ResultCode.SUCCESS,data);
    }

    /**
     * 失败响应，仅状态
     *
     * @param resultCode
     * @return com.biocv.base.vo.Result
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-17 11:24
     * @since 1.0.0
    */
    public static BioCVResult fail(ResultCode resultCode){
        BioCVResult bioCVResult = new BioCVResult();
        bioCVResult.setCode(resultCode.getCode());
        bioCVResult.setMessage(resultCode.getMessage());
        return bioCVResult;
    }

    /**
     * 失败响应，包含数据
     *
     * @param resultCode
     * @param data
     * @return com.biocv.base.vo.Result
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-17 11:24
     * @since 1.0.0
    */
    public static BioCVResult fail(ResultCode resultCode, Object data){
        BioCVResult bioCVResult = new BioCVResult();
        bioCVResult.setCode(resultCode.getCode());
        bioCVResult.setMessage(resultCode.getMessage());
        bioCVResult.setData(data);
        return bioCVResult;
    }
}
