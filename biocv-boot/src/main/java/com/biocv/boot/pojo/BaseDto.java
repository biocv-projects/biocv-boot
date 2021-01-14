package com.biocv.boot.pojo;

import lombok.Data;

/**
 * Data Transfer Object：返回接口使用。据传输对象，Service或Manager向外传输的对象。
 * @author kai
 * @date 2020/12/18 16:41
 */
@Data
public abstract class BaseDto {

    private String id;

}
