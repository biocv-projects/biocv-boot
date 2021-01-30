package com.biocv.boot.protocol.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 心跳请求返回
 *
 * @author Tyler.Feng
 * @date 2021-01-18 15:24
 * @since 1.0.0
 */
@Data
public class HeartbeatResponse implements Serializable {
     /** 后台等待下发命令 0: 无命令, 1: 有命令下发*/
     private int command;

     /** 后台分配通信密匙 */
     private String secret;

     /** 后台分配的TCP的ip地址 */
     private String ip;

     /** 后台分配的TCP的端口 */
     private int port;
}
