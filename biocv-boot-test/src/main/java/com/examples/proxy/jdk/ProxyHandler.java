package com.examples.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author Tyler.Feng
 * @date 2021-01-19 11:08
 * @since 1.0.0
 */
public class ProxyHandler implements InvocationHandler {

    private Object object;

    public ProxyHandler(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理：执行前" + method.getName());
        method.invoke(object,args);
        System.out.println("代理：执行后" + method.getName());
        return null;
    }
}
