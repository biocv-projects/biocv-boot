package com.biocv.boot.protocol.provider;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理器提供者
 *
 * @author Tyler.Feng
 * @date 2021-01-18 11:39
 * @since 1.0.0
 */
public interface ProtocolProvider {

    /**
     * 处理
     *
     * @param ctx
     * @param jsonObject
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-18 11:40
     * @since 1.0.0
     */
    void process(ChannelHandlerContext ctx, JSONObject jsonObject);

    /**
     * 是否支持
     *
     * @param jsonObject
     * @return java.lang.Boolean
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-18 11:41
     * @since 1.0.0
     */
    boolean supports(JSONObject jsonObject);

}
