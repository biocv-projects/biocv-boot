package com.examples.proxy.cglib;

/**
 * 目标类
 *
 * @author Tyler.Feng
 * @date 2021-01-19 11:45
 * @since 1.0.0
 */
public class TargetServiceImpl {

    /**
     * 做业务
     *
     * @return void
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-19 11:46
     * @since 1.0.0
     */
    public void doBusiness(){
        System.out.println("目标方法执行");
    }

}
