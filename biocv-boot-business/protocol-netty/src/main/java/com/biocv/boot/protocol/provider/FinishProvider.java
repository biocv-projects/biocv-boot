package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理结束
 *
 * @author Tyler.Feng
 * @date 2021-01-18 15:00
 * @since 1.0.0
 */
public class FinishProvider implements ProtocolProvider {
    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //ctx.writeAndFlush(ResultBean.success("设备数据上传结束"));
        ctx.close();
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");
        if ("device.upload.finish".equals(funcId)){
            return true;
        }
        return false;
    }
}
