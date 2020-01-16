package com.example.plug_tool;

import android.content.Context;

public interface ApkManagerInterface {

    //加载插件apk时初始化
    void init();
    //加载apk
    void load(String keyName, String apkPath, String dexOutPath, Context context);
    //获取对应的插件apk
    ApkHelper get(String keyName);
}
