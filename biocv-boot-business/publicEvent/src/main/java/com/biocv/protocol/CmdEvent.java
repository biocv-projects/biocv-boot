package com.biocv.protocol;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 命令事件
 *
 * @author Tyler.Feng
 * @date 2021-02-01 11:14
 * @since 1.0.0
 */
@Getter
public class CmdEvent extends ApplicationEvent {

    private final String content;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public CmdEvent(Object source, String content) {
        super(source);
        this.content = content;
    }
}
