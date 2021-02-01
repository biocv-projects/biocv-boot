package com.biocv.boot.protocol.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * json对象编码成字节流
 *
 * @author Tyler.Feng
 * @date 2021-01-18 14:35
 * @since 1.0.0
 */
public class JsonEncoder extends MessageToByteEncoder<ResultBean> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ResultBean msg, ByteBuf out) throws Exception {
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSON(msg).toString());
        String result = jsonObject.toJSONString();
        out.writeBytes(result.getBytes("utf-8"));
    }
}
