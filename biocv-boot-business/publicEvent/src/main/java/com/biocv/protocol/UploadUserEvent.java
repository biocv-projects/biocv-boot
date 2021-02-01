package com.biocv.protocol;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * 上传用户事件
 *
 * @author Tyler.Feng
 * @date 2021-02-01 11:00
 * @since 1.0.0
 */
@Getter
@Setter
public class UploadUserEvent extends ApplicationEvent {

    /**
     * sn
     */
    private String sn;

    /**
     * pin
     */
    private String pin;

    /**
     * 身份证号码
     */
    private String identityNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 国家
     */
    private String nation;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birth;

    /**
     * 地址
     */
    private String address;

    /**
     * 卡号
     */
    private String card;

    /**
     * 电话
     */
    private String phone;

    /**
     * 身份证头像
     */
    private String identityPhoto;

    /**
     * 比对照片
     */
    private String photo;

    /**
     * 指纹模板
     */
    private List<FingerPrint> fingerPrintList;

    /**
     * 指纹
     *
     * @author Tyler.Feng
     * @date 2021-02-01 10:58
     * @since 1.0.0
     */
    @Getter
    @Setter
    static class FingerPrint{

        /**
         * index
         */
        private int index;

        /**
         * 模板
         */
        private String template;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UploadUserEvent(Object source) {
        super(source);
    }

}
