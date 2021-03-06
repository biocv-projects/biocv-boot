package com.biocv.protocol;

import org.springframework.context.ApplicationEvent;

/**
 * 心跳事件
 *
 * @author Tyler.Feng
 * @date 2021-02-01 10:47
 * @since 1.0.0
 */
public class HeartBeatEvent extends ApplicationEvent {

    /**
     * SN
     */
    private final String sn;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public HeartBeatEvent(Object source, String sn) {
        super(source);
        this.sn = sn;
    }
}
