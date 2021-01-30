package com.biocv.boot.protocol.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: amos.chen
 * @description:
 * @time: 2021/1/26  19:59
 */
@Getter
@Setter
public class Params {
    /**
     * 工号
     */
    private String pin;

    /**
     * 生物识别类型。( 1: 指纹, 2: 人脸) 若为空，则标识删除这个工号的所有模板
     */
    private String type;

    /**
     * 生物具体个体编号, 为空时表示删除全部指定type的模板信息
     */
    private String index;

    /**
     * 设备号
     */
    private String sn;

    /**
     * 后台分配通信密匙
     */
    private String secret;

    /**
     * 固件版本(客户端版本)号
     */
    @JSONField(name="FWVersion")
    private String fwVersion;

    /**
     * 最大用户数
     */
    @JSONField(name="~MaxUserCount")
    private String maxUserCount;

    /**
     * 最大核验记录数
     */
    @JSONField(name="~MaxAttLogCount")
    private String maxAttlogCount;

    /**
     * 指静脉开关
     */
    @JSONField(name="FingerFunOn")
    private String fingerFunOn;

    /**
     * 指纹模板
     */
    @JSONField(name="FPVersion")
    private String fpVersion;

    /**
     * 最大指静脉模板数
     */
    @JSONField(name="~MaxFingerCount")
    private String maxFingerCount;

    /**
     * 是否支持面部功能
     */
    @JSONField(name="FaceFunOn")
    private String faceFunOn;

    /**
     * 最大面部模板数
     */
    @JSONField(name="~MaxFaceCount")
    private String maxFaceCount;

    /**
     * 是否支持照片功能
     */
    @JSONField(name="PhotoFunOn")
    private String photoFunOn;

    /**
     * 最大用户照片数
     */
    @JSONField(name="~MaxUserPhotoCount")
    private String maxUserPhotoCount;

    /**
     * 是否上传核验照片
     */
    @JSONField(name="IsUploadCheckPic")
    private String isUploadCheckPic;

    /**
     * 设备ip地址
     */
    @JSONField(name="IPAddress")
    private String iPAddress;

    /**
     * 设备当前指纹模板数
     */
    @JSONField(name="FPCount")
    private String fpCount;

    /**
     * 设备当前用户数
     */
    @JSONField(name="UserCount")
    private String userCount;

    /**
     * 设备当前面部模板数
     */
    @JSONField(name="FaceCount")
    private String faceCount;

    /**
     * mac地址
     */
    @JSONField(name="MAC")
    private String mac;

    /**
     * 设备名称
     */
    @JSONField(name="~DeviceName")
    private String deviceName;

    /**
     * 是否许可
     */
    @JSONField(name="IsLicense")
    private String isLicense;

    /**
     * 算法机器码
     */
    @JSONField(name="~MachineCode")
    private String machineCode;

    /**
     * 算法机器码版本
     */
    @JSONField(name="MachineCodeVersion")
    private String machineCodeVersion;

    /** user参数 */
     private Users users;

}
