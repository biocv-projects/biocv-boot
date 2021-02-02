package com.biocv.boot.protocol.sender;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.provider.ProtocolProvider;
import io.netty.channel.ChannelHandlerContext;

/**
 * 清除所有用户
 * @author: amos.chen
 * @description:
 * @time: 2021/1/27  11:26
 */
public class ClearUserSender implements ProtocolProvider {
    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        ctx.writeAndFlush(ResultBean.sendSuccess("清除所有用户","cmd.clear.user"));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        //TODO 不知已什么条件来确定进行这个下发请求，暂时采用循环发送所有下发命令
//        JSONObject payload = jsonObject.getJSONObject("payload");
//        JSONObject params = payload.getJSONObject("params");
//        Integer command = params.getInteger("command");
        return 1 == 1;
    }
}
