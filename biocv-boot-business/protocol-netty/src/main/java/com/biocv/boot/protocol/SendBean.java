package com.biocv.boot.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 下发命令
 * @author: amos.chen
 * @description:
 * @time: 2021/1/27  10:06
 */
@Data
public class SendBean  implements Serializable {

    /**
     * 命令id
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
    public SendBean(){

    }

    public SendBean(String funcId) {
        this.funcId = funcId;
    }

    /**
     * 下发命令不带参数
     * @author amos.chen
     * @date  2021/1/27 11:19
     * @param
     * @return com.zkteco.zkbiosecurity.SendBean
     */
    public static SendBean sendOrder(String funcId){
        SendBean sendBean = new SendBean();
        sendBean.setFuncId(funcId);
        return sendBean;

    }

   /**
    * 下发命令带参数
    * @author amos.chen
    * @date  2021/1/27 10:22
    * @param funcId,result
    * @return com.zkteco.zkbiosecurity.SendBean
    */
    public static SendBean sendOrder(String funcId,Object result){
        SendBean sendBean = new SendBean();
        sendBean.setFuncId(funcId);
        Payload payload = new Payload();
        payload.setParams(result);
        sendBean.setPayload(payload);
        return sendBean;
    }
}


