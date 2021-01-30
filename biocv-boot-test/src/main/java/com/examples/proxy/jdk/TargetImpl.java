package com.examples.proxy.jdk;

/**
 * 目标接口默认实现
 *
 * @author Tyler.Feng
 * @date 2021-01-19 11:05
 * @since 1.0.0
 */
public class TargetImpl implements TargetInterface{

    @Override
    public void doBusiness() {
        System.out.println("目标方法实行业务");
    }
}
