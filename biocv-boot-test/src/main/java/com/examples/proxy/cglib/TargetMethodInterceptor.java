package com.examples.proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 增强其
 *
 * @author Tyler.Feng
 * @date 2021-01-19 11:50
 * @since 1.0.0
 */
public class TargetMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before:" + method.getName());
        methodProxy.invokeSuper(o,objects);
        System.out.println("after:" + method.getName());
        return o;
    }
}
