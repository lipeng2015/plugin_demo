package com.example.plug_tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import dalvik.system.DexClassLoader;

/**
 * 加载插件apk的相关信息
 */
public class ApkHelperInterfaceImp implements ApkHelperInterface{

    private static final String TAG = "ApkHelperInterfaceImp";
    private String apkPath;
    private Context ctx;

    private Drawable appIcon;
    private String apkName;

    private PackageInfo packageInfo;
    private DexClassLoader dexClassLoader;
    private Resources resources;
    private Resources.Theme theme;
    @Override
    public void init(String apkPath, String dexOutPath, Context context) {
        this.apkPath = apkPath;
        this.ctx = context;
        //获取插件apk的包信息
        packageInfo = PlugUtils.getPackageInfo(context,apkPath);
        appIcon = PlugUtils.getAppIcon(context, apkPath);
        apkName = (String) PlugUtils.getAppLabel(context, apkPath);
        //获取插件apk包的资源
        resources= PlugUtils.readApkRes(context,apkPath);
        this.theme = resources.newTheme();
        this.theme.applyStyle(R.style.PlugTheme,false);
        //获取插件apk的classLoader
        dexClassLoader= PlugUtils.readDexFile(context,apkPath,dexOutPath);
    }

    @Override
    public Class<?> getClassById(String keyName) {
        Class<?> aClass =null;
        try {
            String string = packageInfo.applicationInfo.metaData.getString(keyName);
            aClass = dexClassLoader.loadClass(string);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,""+e.getMessage());
        }
        return aClass;
    }

    @Override
    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    @Override
    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    @Override
    public Resources getResources() {
        return resources;
    }

    @Override
    public Resources.Theme getTheme() {
        return theme;
    }
}
