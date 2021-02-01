package com.biocv.boot.vis.cmd.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CMD")
public class Cmd {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    /**
     * func id
     */
    @Column(name = "FUNC_ID")
    private String funcId;

    /**
     * 请求字符串
     */
    @Column(name = "REQUEST_STR",length = 5000)
    private String requestStr;

    /**
     * 请求时间
     */
    @Column(name = "REQUEST_TIME")
    private Long requestTime;

    /**
     * 响应字符串
     */
    @Column(name = "RESPONSE_STR")
    private String responseStr;

    /**
     * 响应时间
     */
    @Column(name = "RESPONSE_TIME")
    private Long responseTime;

}
