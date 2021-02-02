package com.biocv.boot;

import java.io.Serializable;

/**
 * page
 * @author kai
 * @date 2021/2/1 19:33
 */
public abstract class Page {

    private int pageIndex;

    private int pageSize;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
