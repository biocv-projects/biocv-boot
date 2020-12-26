package com.biocv.boot.autoconfigure.logging;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日志实体类
 *
 * @author kai
 * @date 2020/12/25 17:05
 */
@Entity
@Table(name = "SYSTEM_LOG")
@Getter
@Setter
@Accessors(chain = true)
public class SystemLog {
    private String id;

    /** 时间戳 */
    @Column(name = "TIMESTAMP")
    private String timestamp;

    /**
     * 远程请求地址
     */
    @Column(name = "ORIGIN_ADDRESS")
    private String originAddress;

    /**
     * 请求的url
     */
    @Column(name = "URL")
    private String url;

    /**
     * 被请求的方法
     */
    @Column(name = "METHOD")
    private String method;

    /** 请求参数 */
    @Column(name = "REQUEST_PARAMTER")
    private String requestParameter;

    /** 返回值 */
    @Column(name = "RETURN_VALUE")
    private String returnValue;

    /**
     * 异常信息
     */
    @Column(name = "EXCEPTION")
    private String exception;


    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
