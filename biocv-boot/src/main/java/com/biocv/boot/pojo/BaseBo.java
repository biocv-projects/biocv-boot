package com.biocv.boot.pojo;

import com.biocv.boot.Page;

/**
 * Business Object：service层使用。业务对象。 由Service层输出的封装业务逻辑的对象。
 *
 * @author kai
 * @date 2020/12/18 16:47
 */
public abstract class BaseBo extends Page {

    /**
     * id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
