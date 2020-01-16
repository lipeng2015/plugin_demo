package com.example.plug_tool;

import android.content.Context;

import java.util.HashMap;

public class ApkManagerInterfaceImp implements ApkManagerInterface {

    private HashMap<String, ApkHelper> apkHelperHashMap;

    //初始化插件apk的管理集合，方便管理多个插件apk
    @Override
    public void init() {
        apkHelperHashMap=new HashMap<>();
    }

    //加载插件apk时存入集合
    @Override
    public void load(String keyName, String apkPath, String dexOutPath, Context context) {
        //实例化插件apk的帮助者
        ApkHelper apkHelper = new ApkHelper(apkPath, dexOutPath, context);
        apkHelperHashMap.put(keyName, apkHelper);
    }

    //根据插件apk的名字获取对应的ApkHelper
    @Override
    public ApkHelper get(String keyName) {
        return apkHelperHashMap.get(keyName);
    }
}
