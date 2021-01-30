package com.biocv.boot.vis.staff.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 员工表
 *
 * @author Tyler.Feng
 * @date 2021-01-12 16:45
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "STAFF")
public class Staff implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * 工号
     */
    @Column(name = "PIN")
    private String pin;

    /**
     * 证件号
     */
    @Column(name = "IDENTITY_NUMBER")
    private String identityNumber;

    /**
     * 名字
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 国籍
     */
    @Column(name = "nation")
    private String nation;

    /**
     * 性别
     */
    @Column(name = "SEX")
    private short sex;

    /**
     * 生日
     */
    @Column(name = "BIRTH")
    private String birth;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 照片路径
     */
    @Column(name = "CARD")
    private String card;

    /**
     * 电话
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * 证件照
     */
    @Column(name = "IDENTITY_PHOTO")
    private String identityPhoto;

    /**
     * 比对照片
     */
    @Column(name = "PHOTO")
    private String photo;

}
