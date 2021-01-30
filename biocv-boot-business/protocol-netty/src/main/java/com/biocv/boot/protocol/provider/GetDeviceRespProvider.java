package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.params.Params;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: amos.chen
 * @description:
 * @time: 2021/1/27  14:04
 */
public class GetDeviceRespProvider implements ProtocolProvider {
    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        // json 转 bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject param = payload.getJSONObject("params");
        Params params = JSON.parseObject(param.toJSONString(),Params.class);
        System.out.println("成功获取设备特性");
        //TODO
        ctx.writeAndFlush(ResultBean.success("成功获取设备特性",params));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");
        if ("device.option.upload".equals(funcId)){
            return true;
        }
        return false;
    }
}
