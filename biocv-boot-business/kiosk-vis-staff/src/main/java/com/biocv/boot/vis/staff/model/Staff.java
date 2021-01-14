package com.biocv.boot.vis.staff.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
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
     * 名字
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 姓氏
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 照片路径
     */
    @Column(name = "PHOTO")
    private String photo;

}
