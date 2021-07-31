package com.jvm.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiyang
 * @description 基于Cglib的代理
 * @create 2021/7/31 21:26
 */
public class CglibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new DebugInterceptor());
        enhancer.setClassLoader(SmsMessageSendService.class.getClassLoader());
        enhancer.setSuperclass(SmsMessageSendService.class);
        SmsMessageSendService smsMessageSendService = (SmsMessageSendService) enhancer.create();
        smsMessageSendService.send("hello msg");
        List<SmsMessageSendServiceImpl> list = new ArrayList<>();
    }
}


class SmsMessageSendService {

    public void send(String msg) {
        System.out.println("send sms msg: " + msg);
    }
}

class DebugInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("interceptor before");
//        method.invoke(o, objects);
        methodProxy.invokeSuper(o, objects);
        System.out.println("interceptor after");
        return null;
    }
}
