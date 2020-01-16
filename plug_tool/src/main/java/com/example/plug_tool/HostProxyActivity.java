package com.example.plug_tool;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plug_tool.plugin.PluginInterface;

public abstract class HostProxyActivity extends AppCompatActivity {

    private static final String TAG = "HostProxyActivity";
    private HostProxyHelper hostProxyHelper;
    private PluginInterface proxyClass;
    private String apkName;
    private String classTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //获取要跳转页面的配置信息
        getIntentParam();
        //实例化代理类的实现
        hostProxyHelper = new HostProxyHelper(this);
        //获取要加载apk的信息
        proxyClass = hostProxyHelper.getBaserProxy(apkName, classTag);
        super.onCreate(savedInstanceState);
        proxyClass.onCreate(savedInstanceState,this);
    }

    private void getIntentParam() {
        //跳转的插件apk
        apkName = getIntent().getStringExtra(PlugConfig.APK_NAME);
        //跳转的插件apk的key
        classTag=getIntent().getStringExtra(PlugConfig.CLASS_TAG);
        Log.e(TAG,"apkName="+apkName+"    classTag="+classTag);
        //为null的话跳自身的页面
        if (apkName==null)
            apkName = getApkKeyName();

        if (classTag==null)
            classTag = getClassTag();
    }

    public abstract String getApkKeyName();
    public abstract String getClassTag();

    @Override
    protected void onDestroy() {
        proxyClass.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        proxyClass.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        proxyClass.onResume();
    }

    @Override
    protected void onPause() {
        proxyClass.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        proxyClass.onStop();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        proxyClass.onRestart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        proxyClass.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        proxyClass.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {
        proxyClass.onBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        proxyClass.onSaveInstanceState(outState);
    }

    @Override
    public Resources getResources() {
        if (hostProxyHelper != null && hostProxyHelper.isInit()) {
            return hostProxyHelper.getResources();
        }
        return super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        if (hostProxyHelper != null && hostProxyHelper.isInit()) {
            return hostProxyHelper.getAssets();
        }
        return super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (hostProxyHelper != null && hostProxyHelper.isInit()) {
            return hostProxyHelper.getClassLoader();
        }
        return super.getClassLoader();
    }

    @Override
    public Resources.Theme getTheme() {
        if (hostProxyHelper !=null && hostProxyHelper.isInit())
        {
            return hostProxyHelper.getTheme();
        }
        return super.getTheme();
    }
}
