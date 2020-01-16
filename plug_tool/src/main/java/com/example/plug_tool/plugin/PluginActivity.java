package com.example.plug_tool.plugin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plug_tool.PlugConfig;
import com.example.plug_tool.PlugUtils;

/**
 * 根activity需要继承PluginActivity
 * 根据meta定义的classValue进行页面分发
 */
public abstract class PluginActivity extends AppCompatActivity {
    private PluginInterface proxyClass;
    private static final String TAG = "PluginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取启动activity时的标签
        getIntentParam();
        proxyClass.onCreate(savedInstanceState,this);
    }

    private void getIntentParam() {
        try {
            String classTag = getIntent().getStringExtra(PlugConfig.CLASS_TAG);
            //获取AndroidManifest.xml中定义的class
            PackageInfo packageInfo2 = PlugUtils.getPackageInfo2(this, getPackageName());
            String className = packageInfo2.applicationInfo.metaData.getString(classTag);
            Log.i(TAG,"classTag="+classTag+"   className="+className);
            //获取到class全类名后根据类加载器进行加载
            proxyClass = (PluginInterface) getClassLoader().loadClass(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (proxyClass==null)
            proxyClass=getProxyBase();
    }

    public abstract PluginInterface getProxyBase();

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
}
