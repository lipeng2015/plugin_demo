package com.example.plug_tool;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.plug_tool.plugin.PluginInterface;

public interface HostProxyInterface {

    void init(Activity activity);

    boolean isInit();

    PluginInterface getBaserProxy(String keyName, String classTag);

    Resources getResources();

    AssetManager getAssets();

    ClassLoader getClassLoader();

    Resources.Theme getTheme();

    PackageInfo getPackageInfo();
}
