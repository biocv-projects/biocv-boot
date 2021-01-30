package com.biocv.boot.protocol;


import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果
 *
 * @author Tyler.Feng
 * @date 2021-01-18 14:17
 * @since 1.0.0
 */
@Data
public class ResultBean implements Serializable {

    /**
     * 相应码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 命令码
     */
    private String funcId;

    /**
     * 下发命令对象
     */
    private Payload payload;

    @Data
    public static class Payload{

       private Object params;

    }

    /**
     * 构造方法
     */
    public ResultBean(){

    }

    public ResultBean(String message){
        this.message = message;
    }

    public ResultBean(int code, String message){
        this.code = code;
        this.message = message;
    }
    public ResultBean(String message,String funcId){
        this.message = message;
        this.funcId = funcId;
    }

    /**
     * 返回成功
     *
     * @param message
     * @return com.zkteco.zkbiosecurity.ResultBean
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-18 14:21
     * @since 1.0.0
     */
    public static ResultBean success(String message){
        return new ResultBean(ProtocolConstants.SUCCESS,message);
    }

    /**
     * 返回带结果的成功
     *
     * @param message
     * @param result
     * @return com.zkteco.zkbiosecurity.ResultBean
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-18 15:35
     * @since 1.0.0
     */
    public static ResultBean success(String message, Object result){
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ProtocolConstants.SUCCESS);
        resultBean.setMessage(message);
        Payload payload = new Payload();
        payload.setParams(result);
        resultBean.setPayload(payload);
        return resultBean;
    }

    /**
     * 下发命令不带参数
     * @author amos.chen
     * @date  2021/1/27 16:53
     * @param funcId
     * @return com.zkteco.zkbiosecurity.ResultBean
     */
    public static ResultBean sendSuccess(String message,String funcId){
        return new ResultBean(message,funcId);
    }

    public static ResultBean sendSuccess(String message,String funcId,Object object){
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ProtocolConstants.SUCCESS);
        resultBean.setMessage(message);
        resultBean.setFuncId(funcId);
        Payload payload = new Payload();
        payload.setParams(object);
        resultBean.setPayload(payload);
        return resultBean;
    }

}
