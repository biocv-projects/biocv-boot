package com.biocv.boot.protocol.sender;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.provider.ProtocolProvider;
import io.netty.channel.ChannelHandlerContext;

/**
 * 设备请求下发命令
 * @author: amos.chen
 * @description:
 * @time: 2021/1/26  16:34
 */
public class ServerOrderSender implements ProtocolProvider {

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //json 转 bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");

        System.out.println("接收到设备请求下发命令");


    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");
        return "device.upload.command".equals(funcId);
    }
}
