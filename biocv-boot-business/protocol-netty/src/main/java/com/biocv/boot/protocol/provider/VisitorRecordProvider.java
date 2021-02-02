package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.params.LogParam;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: amos.chen
 * @description:
 * @time: 2021/1/19  9:54
 */
public class VisitorRecordProvider  implements ProtocolProvider{

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //JSON 转 bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");
        JSONObject log = params.getJSONObject("log");

        LogParam logParam = JSON.parseObject(log.toJSONString(),LogParam.class);
        // TODO 处理

        ctx.writeAndFlush(ResultBean.success("访客记录上传成功"));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");

        return "device.upload.visitlog".equals(funcId);
    }
}
