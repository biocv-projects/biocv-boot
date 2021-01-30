package com.biocv.boot.vis.device.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 眼镜设备 model
 *
 * @author Tyler.Feng
 * @date 2021-01-29 15:35
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "GLASSES_DEVICE")
public class GlassesDevice {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * SN
     */
    @Column(name = "SN")
    private String sn;

    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;

}
