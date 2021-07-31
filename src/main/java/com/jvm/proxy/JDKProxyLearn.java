package com.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tangweiyang
 * @description 动态代理
 * @create 2021/7/31 20:10
 */
public class JDKProxyLearn {
    public static void main(String[] args) throws Throwable {
        SmsMessageSendServiceImpl smsMessageSendService = new SmsMessageSendServiceImpl();
        System.out.println(smsMessageSendService.getClass().getDeclaredMethod("send",String.class));
        MessageSendService messageSendService = (MessageSendService) Proxy.newProxyInstance(
                smsMessageSendService.getClass().getClassLoader(),
                smsMessageSendService.getClass().getInterfaces(),
                new CommonProxy(smsMessageSendService));
        messageSendService.send("jdk proxy send");
//        MessageSendService messageSendService = new MsgSendServiceProxy(smsMessageSendService);
//        messageSendService.send("myself msg send");
    }
}

interface MessageSendService {
    void send(String msg) throws Throwable;
}

class SmsMessageSendServiceImpl implements MessageSendService {

    @Override
    public void send(String msg) {
        System.out.println("send sms msg: " + msg);
    }
}


class CommonProxy implements InvocationHandler {

    Object target;

    public CommonProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy before");
        Object res = method.invoke(target, args);
        System.out.println("proxy after");
        return res;
    }
}

class MsgSendServiceProxy extends CommonProxy implements MessageSendService {

    public MsgSendServiceProxy(Object target) {
        // 获取target 组合
        super(target);
    }

    @Override
    public void send(String msg) throws Throwable {
        System.out.println(target.getClass());
        // 委托
        super.invoke(null, target.getClass().getDeclaredMethod("send",String.class), new Object[]{msg});
    }
}