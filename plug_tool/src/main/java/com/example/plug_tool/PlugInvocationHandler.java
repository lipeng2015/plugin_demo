package com.example.plug_tool;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PlugInvocationHandler implements InvocationHandler {

    public static final String tag="PlugInvocationHandler";
    private Object obj;

    public Object bind(Class<?> name){
        Log.i(tag, "interface name="+name);
        obj = PlugUtils.getObj(name.getName(), getClass().getClassLoader());
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i(tag, "method name=="+ method.getName());
        Object invoke = method.invoke(obj, args);
        return invoke;
    }
}
