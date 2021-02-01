package com.biocv.boot.protocol;

import com.biocv.boot.protocol.handler.DataHandler;
import com.biocv.boot.protocol.handler.JsonEncoder;
import com.biocv.boot.protocol.handler.JsonHandler;
import com.biocv.boot.protocol.provider.ProviderManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * handler初始化
 *
 * @author Tyler.Feng
 * @date 2021-01-15 19:12
 * @since 1.0.0
 */
public class GlassesInitializer extends ChannelInitializer<SocketChannel> {

    private ProviderManager providerManager;

    public GlassesInitializer(ProviderManager providerManager){
        this.providerManager = providerManager;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("init channel");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
//        pipeline.addLast(new LengthFieldPrepender(4));
        //字符串解码
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        //字符串编码
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //读超时时间设置为10s，0表示不监控
//        pipeline.addLast(new IdleStateHandler(10, 4, 0, TimeUnit.SECONDS));
        pipeline.addLast(new JsonObjectDecoder());
        pipeline.addLast(new JsonEncoder());
        pipeline.addLast(new DataHandler());
        pipeline.addLast(new JsonHandler(providerManager));
    }
}
