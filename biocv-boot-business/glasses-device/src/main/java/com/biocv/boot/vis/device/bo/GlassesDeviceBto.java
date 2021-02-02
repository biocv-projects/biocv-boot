package com.biocv.boot.vis.device.bo;

import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 眼镜设备bo
 *
 * @author Tyler.Feng
 * @date 2021-01-29 15:37
 * @since 1.0.0
 */
@Getter
@Setter
public class GlassesDeviceBto extends BaseBo implements Serializable {

    @QueryCondition(column = "id")
    private String id;

    /**
     * SN
     */
    @QueryCondition(column = "sn")
    private String sn;

    /**
     * IP地址
     */
    @QueryCondition(column = "ip")
    private String ip;

}
