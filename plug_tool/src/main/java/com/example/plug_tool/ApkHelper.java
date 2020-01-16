package com.example.plug_tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class ApkHelper {

    ApkHelperInterface apkHelperInterface;

    public ApkHelper(String apkPath, String dexOutPath, Context context) {
        apkHelperInterface= (ApkHelperInterface) new PlugInvocationHandler().bind(ApkHelperInterface.class);
        apkHelperInterface.init(apkPath,dexOutPath,context);
    }
    public Class<?> getClassById(String keyName){
        return apkHelperInterface.getClassById(keyName);
    }

    public PackageInfo getPackageInfo() {
        return apkHelperInterface.getPackageInfo();
    }

    public DexClassLoader getDexClassLoader() {
        return apkHelperInterface.getDexClassLoader();
    }

    public Resources getResources() {
        return apkHelperInterface.getResources();
    }

    public Resources.Theme getTheme(){
        return apkHelperInterface.getTheme();
    }
}
