package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.params.Users;
import com.biocv.protocol.UploadUserEvent;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 用户服务 provider
 *
 * @author Tyler.Feng
 * @date 2021-01-18 13:50
 * @since 1.0.0
 */
public class UserProvider implements ProtocolProvider, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        //json转 user bean
        JSONObject payload = jsonObject.getJSONObject("payload");
        JSONObject params = payload.getJSONObject("params");
        JSONObject users = params.getJSONObject("users");

        Users userParam = JSON.parseObject(users.toJSONString(), Users.class);

        //发布事件
        UploadUserEvent uploadUserEvent = new UploadUserEvent(this);
        BeanUtils.copyProperties(userParam,uploadUserEvent);
        publisher.publishEvent(uploadUserEvent);

        ctx.writeAndFlush(ResultBean.success("成功了！"));
    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        String funcId = jsonObject.getString("funcId");
        if ("device.upload.user".equals(funcId)){
            return true;
        }
        return false;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = publisher;
    }
}
