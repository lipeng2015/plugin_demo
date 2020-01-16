package com.example.plug_tool;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.plug_tool.plugin.PluginInterface;

public class HostProxyInterfaceImp implements HostProxyInterface {

    private Activity activity;
    private ApkHelper apkHelper;
    @Override
    public void init(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean isInit() {
        if (apkHelper!=null)
            return true;
        return false;
    }

    @Override
    public PluginInterface getBaserProxy(String keyName, String classTag) {
        Class<?> proxy = getProxy(keyName, classTag);
        PluginInterface o =null;
        try {
            o = (PluginInterface) proxy.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    //通过要跳转的apk和配置的key对应的参数获取全类名
    public Class<?> getProxy(String keyName, String classTag){
        if (classTag==null)
        {
            classTag= PlugConfig.ROOT_CLASS_NAME;
        }
        //获取对应apk的apkHelper，就可以调用ApkHelperInterfaceImp的方法
        apkHelper = ApkManager.getInstance().getHelper(keyName);
        //根据插件apk中AndroidManifest.xml配置的key获取对应value全类名
        Class<?> classById = apkHelper.getClassById(classTag);
        return classById;
    }

    @Override
    public Resources getResources() {
        return apkHelper.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return apkHelper.getResources().getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return apkHelper.getDexClassLoader();
    }

    @Override
    public Resources.Theme getTheme() {
        return apkHelper.getTheme();
    }

    @Override
    public PackageInfo getPackageInfo() {
        return apkHelper.getPackageInfo();
    }
}
