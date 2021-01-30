package com.biocv.boot.protocol.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: amos.chen
 * @description:
 * @time: 2021/1/27  8:58
 */
@Getter
@Setter
public class FingerprintBean {
    /**
     * 索引号
     */
    @JSONField(name = "index")
    private String index;

    /**
     * 模板base64
     */
    @JSONField(name = "template")
    private String template;
}
