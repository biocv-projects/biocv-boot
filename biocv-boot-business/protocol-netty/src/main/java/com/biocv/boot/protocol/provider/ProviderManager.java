package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.sender.*;
import com.biocv.protocol.CmdEvent;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理管理器
 *
 * @author Tyler.Feng
 * @date 2021-01-18 15:11
 * @since 1.0.0
 */
@Component
public class ProviderManager implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private final List<ProtocolProvider> providers ;

    public ProviderManager(){
        providers = new ArrayList<>();
        providers.add(new HeartbeatProvider());
        providers.add(new UserProvider());
        providers.add(new WhiteListProvider());
        providers.add(new BlackListProvider());
        providers.add(new VerificationProvider());
        providers.add(new GetDeviceRespProvider());
        providers.add(new VisitorRecordProvider());
        providers.add(new FinishProvider());
        providers.add(new ServerOrderSender());
        providers.add(new PersonnelMsgSender());
        providers.add(new DeletePersonSender());
        providers.add(new ClearAllSender());
        providers.add(new ClearChecklogSender());
        providers.add(new ClearUserSender());
        providers.add(new GetDeviceSender());
        providers.add(new FinishSender());
    }

    /**
     * 遍历处理器处理
     *
     * @param ctx
     * @param jsonObject
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-18 15:11
     * @since 1.0.0
     */
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) throws InterruptedException {
        //1.发布命令到来事件
        publisher.publishEvent(new CmdEvent(this,jsonObject.toJSONString()));
        //2.开始处理
        for (ProtocolProvider provider : providers){
            if (!provider.supports(jsonObject)){
                continue;
            }
            provider.process(ctx, jsonObject);
        }
        Thread.sleep(2000);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
