package com.biocv.boot.protocol.params;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: amos.chen
 * @description:
 * @time: 2021/1/19  9:31
 */
public class LogParam {

    /** 工号 */
    private String pin;

    /** 姓名 */
    private String name;

    /** 民族 */
    private String nation;

    /** 性别. 1: 男, 2: 女, 0: 未知(特殊群体) */
    private String sex;

    /** 出生日期 */
    private String birth;

    /** 身份证住址 */
    private String address;

    /** 身份证签发机关*/
    private String depart;

    /** 核验结果 0: 核验通过, 1: 核验失败, 2: 黑名单*/
    private String status;

    /** 核验时间 yyyy-MM-dd HH:mm:ss */
    private String date;

    /** 核验照片base64字符串 */
    private String picture;

    /** 方向 0: 未知, 1: 进, 2: 出 */
    private String direction;


    /** 身份证头像base64字符串*/
    @JSONField(name="identity_photo")
    private String identityPhoto;

    /**
     * 核验记录：核验类型 1: 卡识别, 256: 指纹识别, 4096: 人脸识别
     * 访客记录：核验类型 1: 身份证+人脸, 2: 身份证+人脸+指纹, 3: 身份证+指纹+人脸, 4: CTID, 5: 身份证+人+ CTID, 6: 仅身份证, 7: 其他证件登记 )
     * */
    @JSONField(name="verify_type")
    private String verifType;

    /** 英文名 */
    @JSONField(name="english_name")
    private String englishName;

    /** 签发次数 */
    @JSONField(name="number_of_issues")
    private String numberOfIssues;

    /** 证件号码 */
    @JSONField(name="certificate_number")
    private String certificateNumber;

    /** 证件类型. (1: 居民身份证, 2: 外国人永居证, 3: 港澳台居住证) */
    @JSONField(name="card_type")
    private String cardType;

    /** 身份证有效期限 */
    @JSONField(name="validity_time")
    private String validityTime;

    /** 身份证号码 */
    @JSONField(name="identity_number")
    private String identityNumber;

    /**
     * 人员信息上传：核验类型 1: 卡识别, 256: 指纹识别, 4096: 人脸识别
     * 访客记录上传：核验类型 1: 身份证+人脸, 2: 身份证+人脸+指纹, 3: 身份证+指纹+人脸, 4: CTID, 5: 身份证+人+ CTID, 6: 仅身份证, 7: 其他证件登记
     * */
    @JSONField(name="verify_type")
    private String verifyType;
}
