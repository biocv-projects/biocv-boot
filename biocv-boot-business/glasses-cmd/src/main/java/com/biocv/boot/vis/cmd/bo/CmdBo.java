package com.biocv.boot.vis.cmd.bo;

import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 命令业务对象
 *
 * @author Tyler.Feng
 * @date 2021-01-29 14:41
 * @since 1.0.0
 */
@Getter
@Setter
public class CmdBo extends BaseBo implements Serializable {

    /**
     * id
     */
    @QueryCondition(column = "id")
    private String id;

    /**
     * SN
     */
    @QueryCondition(column = "sn")
    private String sn;

    /**
     * func id
     */
    @QueryCondition(column = "funcId")
    private String funcId;

    /**
     * 请求字符串
     */
    @QueryCondition(column = "requestStr")
    private String requestStr;

    /**
     * 请求时间
     */
//    @QueryCondition(column = "requestTime")
    private long requestTime;

    /**
     * 响应字符串
     */
    @QueryCondition(column = "responseStr")
    private String responseStr;

    /**
     * 响应时间
     */
//    @QueryCondition(column = "responseTime")
    private long responseTime;

}
