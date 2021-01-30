package com.biocv.boot.protocol.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 数据处理
 *
 * @author Tyler.Feng
 * @date 2021-01-15 19:38
 * @since 1.0.0
 */
public class DataHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);
        System.out.println("获取字符串完毕");
        JSONObject jsonObject = JSON.parseObject(msg);
        ctx.fireChannelRead(jsonObject);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
