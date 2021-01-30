package com.examples.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class App {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetServiceImpl.class);
        enhancer.setCallback(new TargetMethodInterceptor());
        TargetServiceImpl targetService = (TargetServiceImpl)enhancer.create();
        targetService.doBusiness();
    }

}
