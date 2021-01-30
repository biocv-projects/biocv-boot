package com.biocv.boot.protocol.handler;

import com.alibaba.fastjson.JSONObject;
import com.zkteco.zkbiosecurity.ResultBean;
import com.zkteco.zkbiosecurity.provider.ProviderManager;
import com.zkteco.zkbiosecurity.provider.UserProvider;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * json处理器
 *
 * @author Tyler.Feng
 * @date 2021-01-18 10:16
 * @since 1.0.0
 */
public class JsonHandler extends SimpleChannelInboundHandler<JSONObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JSONObject msg) throws Exception {
        System.out.println("获取到json" + msg.toJSONString());
        new ProviderManager().process(ctx,msg);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道关闭");
    }
}
