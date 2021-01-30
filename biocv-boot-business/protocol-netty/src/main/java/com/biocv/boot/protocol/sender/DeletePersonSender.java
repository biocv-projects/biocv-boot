package com.biocv.boot.protocol.sender;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.provider.ProtocolProvider;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除人员命令
 * @author: amos.chen
 * @description:
 * @time: 2021/1/27  10:39
 */
public class DeletePersonSender implements ProtocolProvider {

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        Map<String, ArrayList> params = new HashMap<>();
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        params.put("pin",list);


        ctx.writeAndFlush(ResultBean.sendSuccess("删除人员命令","cmd.delete.user",params));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        //TODO 不知已什么条件来确定进行这个下发请求，暂时采用循环发送所有下发命令
//        JSONObject payload = jsonObject.getJSONObject("payload");
//        JSONObject params = payload.getJSONObject("params");
//        Integer command = params.getInteger("command");
        if (1 == 1){
            return true;
        }
        return false;
    }
}
