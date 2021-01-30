package com.biocv.boot.pojo;

/**
 * Business Object：service层使用。业务对象。 由Service层输出的封装业务逻辑的对象。
 *
 * @author kai
 * @date 2020/12/18 16:47
 */
public class BaseBo {

    private int pageSize;

    private int pageIndex;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
