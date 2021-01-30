package com.biocv.boot;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author Tyler.Feng
 * @date 2021-01-12 16:55
 * @since 1.0.0
 */
@Data
public class Pager implements Serializable {

    /**
     * 当前页码
     */
    private int pageIndex;

    /**
     * 每页数据量
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数据量
     */
    private long total;

    /**
     * 数据
     */
    private List<?> data;

}
