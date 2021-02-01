package com.biocv.boot.protocol.handler;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.provider.ProviderManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * json处理器
 *
 * @author Tyler.Feng
 * @date 2021-01-18 10:16
 * @since 1.0.0
 */
public class JsonHandler extends SimpleChannelInboundHandler<JSONObject> {

    private ProviderManager providerManager;

    public JsonHandler(ProviderManager providerManager){
        this.providerManager = providerManager;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JSONObject msg) throws Exception {
        System.out.println("获取到json" + msg.toJSONString());
        providerManager.process(ctx,msg);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道关闭");
    }
}
