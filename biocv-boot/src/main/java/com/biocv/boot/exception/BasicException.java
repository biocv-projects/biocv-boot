package com.biocv.boot.exception;

/**
 * 自定义异常
 *
 * @author kai
 * @date 2020/12/23 22:47
 */
public class BasicException extends RuntimeException {

    /**
     * 无参
     *
     * @return
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-12-23 22:55
     * @since 1.0.0
    */
    public BasicException() {
        super();
    }

    /**
     * 有参
     *
     * @param message
     * @return
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-12-23 22:55
     * @since 1.0.0
    */
    public BasicException(String message) {
        super(message);
    }

}
