package com.atguigu.springcloud.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ColerProxyMain {

//    public static ColerProxy getColerProxy(final ColerProxy colerProxy){
//        ClassLoader classLoader = colerProxy.getClass().getClassLoader();
//        Class<?>[] interfaces = colerProxy.getClass().getInterfaces();
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
////                System.out.println(method.getName()+"方法执行参数为"+ Arrays.asList(args));
//                Object invoke = method.invoke(colerProxy, args);
////                System.out.println(method.getName()+"方法结果参数为"+ invoke);
//                return invoke;
//            }
//        };
//        Object o = Proxy.newProxyInstance(classLoader,interfaces,handler);
//        return  (ColerProxy)o;
//    }
}
