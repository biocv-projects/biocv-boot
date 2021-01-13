package com.biocv.boot.vis.staff.bo;

import com.biocv.boot.data.MatchType;
import com.biocv.boot.data.QueryCondition;
import com.biocv.boot.pojo.BaseBo;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

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
    @QueryCondition(column = "ID")
    private String id;

    /**
     * 名字
     */
    @QueryCondition(column = "NAME")
    private String name;

    /**
     * 姓氏
     */
    @QueryCondition(column = "LAST_NAME")
    private String lastName;

    /**
     * 邮箱
     */
    @QueryCondition(column = "EMAIL")
    private String email;

    /**
     * 照片路径
     */
    @QueryCondition(column = "PHOTO")
    private String photo;

}
