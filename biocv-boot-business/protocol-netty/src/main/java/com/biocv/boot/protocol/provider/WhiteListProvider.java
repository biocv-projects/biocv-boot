package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.params.Users;
import io.netty.channel.ChannelHandlerContext;

/**
 * 白名单信息上传
 * @author: amos.chen
 * @description:
 * @time: 2021/1/18  19:23
 */
public class WhiteListProvider  implements ProtocolProvider{

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        // json 转 bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");
        JSONObject users = params.getJSONObject("users");

        Users userParam = JSON.parseObject(users.toJSONString(), Users.class);
        //TODO 处理


        ctx.writeAndFlush(ResultBean.success("白名单信息上传成功"));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");

        return "device.upload.whitelist".equals(funcId);
    }
}
