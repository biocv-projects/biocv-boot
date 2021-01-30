package com.biocv.boot.protocol.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author Tyler.Feng
 * @date 2021-01-18 13:39
 * @since 1.0.0
 */
@Setter
@Getter
public class Users implements Serializable {

    /** 工号 */
    private String pin;

    /** 黑/白名单姓名 */
    private String name;

    /** 民族 */
    private String nation;

    /** 性别. 1: 男, 2: 女, 0: 未知(特殊群体) */
    private String sex;

    /** 出生日期 */
    private String birth;

    /** 身份证住址 */
    private String address;

    /** 卡号 */
    private String card;

    /** 手机号 */
    private String phone;

    /** 比对照片base64字符串 */
    private String photo;

    /** 身份证签发机关*/
    private String depart;

    /** 人员类型  0:普通人员 1:Vip用户 2:黑名单人员 3:陌生人 */
    private String category;


    /** 指纹模板信息 */
    private FingerprintBean fingerprintBean;

    /** 证件类型. (1: 居民身份证, 2: 外国人永居证, 3: 港澳台居住证) */
    @JSONField(name="card_type")
    private String cardType;

    /** 身份证有效期限 */
    @JSONField(name="validity_time")
    private String validityTime;

    /** 身份证头像base64字符串*/
    @JSONField(name="identity_photo")
    private String identityPhoto;

    /** 黑/白名单身份证号码 */
    @JSONField(name="identity_number")
    private String identityNumber;

    /** 开始时间，如 2012-03-01 20:01:00*/
    @JSONField(name="start_time")
    private String startTime;

    /** 结束时间，如 2012-03-01 20:01:00*/
    @JSONField(name="end_time")
    private String endTime;

    /** 核验次数（默认-1表示无限次） */
    @JSONField(name="check_num")
    private String checkNum;


}
