package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.response.HeartbeatResponse;
import io.netty.channel.ChannelHandlerContext;

public class HeartbeatProvider implements ProtocolProvider{
    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //json转 user bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");

        System.out.println("接受到心跳"  + params.getString("sn"));

        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        heartbeatResponse.setCommand(0);
        heartbeatResponse.setSecret("xxxxx");
        heartbeatResponse.setIp("192.168.212.159");
        heartbeatResponse.setPort(9527);

        ctx.writeAndFlush(ResultBean.success("我已经接收到心跳了",heartbeatResponse));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");
        if ("device.upload.heartbeat".equals(funcId)){
            return true;
        }
        return false;
    }
}
