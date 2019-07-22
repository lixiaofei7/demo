package com.pandora.boot.demo.mode.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxyHandler implements InvocationHandler {

    private Object target = null;

    public UserProxyHandler(Object target){
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(target,args);
    }

    public static void main(String[] args) {

        User user = new User();
        user.setUserName("张三");
        UserProxyHandler handler = new UserProxyHandler(user);
        UserInterFace userInterFace = (UserInterFace)handler.getProxy();
        userInterFace.say();
    }
}
