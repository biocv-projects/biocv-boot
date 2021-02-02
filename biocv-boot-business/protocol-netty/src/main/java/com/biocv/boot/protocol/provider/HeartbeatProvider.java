package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.response.HeartbeatResponse;
import com.biocv.protocol.HeartBeatEvent;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class HeartbeatProvider implements ProtocolProvider, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //json转 user bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");

        System.out.println("接受到心跳"  + params.getString("sn"));

        //发布事件
        publisher.publishEvent(new HeartBeatEvent(this,params.getString("sn")));

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
        return "device.upload.heartbeat".equals(funcId);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = publisher;
    }
}
