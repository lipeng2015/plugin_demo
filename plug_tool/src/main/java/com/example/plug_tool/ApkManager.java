package com.example.plug_tool;

import android.content.Context;

public class ApkManager {

    private static ApkManager instance = null;

    private ApkManagerInterface apkManagerInterface;

    private ApkManager() {
        apkManagerInterface = (ApkManagerInterface) new PlugInvocationHandler().bind(ApkManagerInterface.class);
        apkManagerInterface.init();
    }

    public static ApkManager getInstance() {
        if (instance == null) {
            synchronized (ApkManager.class) {
                if (instance == null) {
                    instance = new ApkManager();
                }
            }
        }
        return instance;
    }

    public void loadApk(String keyName, String apkPath, String dexOutPath, Context context) {
        apkManagerInterface.load(keyName,apkPath,dexOutPath,context);
    }

    public ApkHelper getHelper(String keyName) {
        return apkManagerInterface.get(keyName);
    }

}
