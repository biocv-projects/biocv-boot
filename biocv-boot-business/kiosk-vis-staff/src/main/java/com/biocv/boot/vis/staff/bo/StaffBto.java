package com.biocv.boot.vis.staff.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工业务对象
 *
 * @author Tyler.Feng
 * @date 2021-01-12 17:01
 * @since 1.0.0
 */
@Data
public class StaffBto extends BaseBo implements Serializable {

   @QueryCondition(column = "id")
    private String id;

    /**
     * 工号
     */
    @QueryCondition(column = "pin")
    private String pin;

    /**
     * 证件号
     */
    @QueryCondition(column = "identityNumber")
    private String identityNumber;

    /**
     * 名字
     */
    @QueryCondition(column = "name")
    private String name;

    /**
     * 国籍
     */
    @QueryCondition(column = "nation")
    private String nation;

    /**
     * 性别
     */
    @QueryCondition(column = "sex")
    private short sex;

    /**
     * 生日
     */
    @QueryCondition(column = "birth")
    private String birth;

    /**
     * 地址
     */
    @QueryCondition(column = "address")
    private String address;

    /**
     * 照片路径
     */
    @QueryCondition(column = "card")
    private String card;

    /**
     * 电话
     */
    @QueryCondition(column = "phone")
    private String phone;

    /**
     * 证件照
     */
    @QueryCondition(column = "identityPhoto")
    @JSONField(name = "identity_photo")
    @JsonProperty("identity_photo")
    private String identityPhoto;

    /**
     * 比对照片
     */
    @QueryCondition(column = "photo")
    private String photo;

}
