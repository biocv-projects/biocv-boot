package com.biocv.boot.autoconfigure.security.dto;

import lombok.Data;

/**
 * 请求认证包装
 *
 * @author kai
 * @date 2020/12/29 14:32
 */
@Data
public class AuthRequest {

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}
