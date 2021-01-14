package com.biocv.boot.vis.staff.bo;

import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工业务对象
 *
 * @author Tyler.Feng
 * @date 2021-01-12 17:01
 * @since 1.0.0
 */
@Data
public class StaffBo extends BaseBo implements Serializable {

    /**
     * id
     */
    @QueryCondition(column = "id")
    private String id;

    /**
     * 名字
     */
    @QueryCondition(column = "name")
    private String testName;

    /**
     * 姓氏
     */
    @QueryCondition(column = "lastName")
    private String lastName;

    /**
     * 邮箱
     */
    @QueryCondition(column = "email")
    private String email;

    /**
     * 照片路径
     */
    @QueryCondition(column = "photo")
    private String photo;

}
