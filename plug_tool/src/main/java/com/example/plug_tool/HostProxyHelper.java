package com.example.plug_tool;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.plug_tool.plugin.PluginInterface;

public class HostProxyHelper {
    HostProxyInterface hostProxyInterface;

    public HostProxyHelper(Activity activity) {
        hostProxyInterface = (HostProxyInterface) new PlugInvocationHandler().bind(HostProxyInterface.class);
        hostProxyInterface.init(activity);
    }

    public boolean isInit(){
        return hostProxyInterface.isInit();
    }

    public PluginInterface getBaserProxy(String keyName, String classTag){
        return hostProxyInterface.getBaserProxy(keyName, classTag);
    }

    public Resources getResources(){
        return hostProxyInterface.getResources();
    }

    public AssetManager getAssets() {
        return hostProxyInterface.getAssets();
    }

    public ClassLoader getClassLoader() {
        return hostProxyInterface.getClassLoader();
    }

    public Resources.Theme getTheme(){
        return hostProxyInterface.getTheme();
    }

    public PackageInfo getPackageInfo(){
        return hostProxyInterface.getPackageInfo();
    }

}
