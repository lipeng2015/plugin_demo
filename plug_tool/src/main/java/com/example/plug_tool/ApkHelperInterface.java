package com.example.plug_tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public interface ApkHelperInterface {

    void init(String apkPath, String dexOutPath, Context context);

    Class<?> getClassById(String keyName);

    PackageInfo getPackageInfo();

    DexClassLoader getDexClassLoader();

    Resources getResources();

    Resources.Theme getTheme();
}
