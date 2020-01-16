package com.pluggable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.plug_tool.ApkManager;
import com.example.plug_tool.PlugConfig;
import com.example.plug_tool.PlugUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity implements Runnable,Handler.Callback, View.OnClickListener {

    public static final String FIRST_APK_KEY="first_apk";


    private Handler handler;

    private TextView showFont;
    private ProgressBar progressBar;
    private Button openOneApk;
    private Button load_apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFont= (TextView) findViewById(R.id.show_font);
        progressBar= (ProgressBar) findViewById(R.id.progressbar);
        openOneApk= (Button) findViewById(R.id.open_one_apk);
        load_apk = findViewById(R.id.load_apk);
        load_apk.setOnClickListener(this);
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        showFont.setText("当前是主宿主apk\n插件apk加载完毕");
        progressBar.setVisibility(View.GONE);
        openOneApk.setVisibility(View.VISIBLE);
        openOneApk.setOnClickListener(this);
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.load_apk){
            showFont.setText("当前是主宿主apk \n 插件apk加载中...");
            progressBar.setVisibility(View.VISIBLE);
            handler=new Handler(this);
            new Thread(this).start();
        }

        if (v.getId()==R.id.open_one_apk) {
            //className配置ROOT_CLASS_NAME，即跳转apk_one的主页面
            //className配置two_class，即跳转apk_one的com.example.apk_one.TwoClass这个页面
            PlugUtils.goActivity(this, FIRST_APK_KEY, PlugConfig.ROOT_CLASS_NAME);
        }

    }

    @Override
    public void run() {
        //为了看加载效果 - -
        SystemClock.sleep(1000);
        String s = "apk_one-debug.apk";
        String dexOutPath="dex_output2";
        File nativeApkPath = PlugUtils.getNativeApkPath(getApplicationContext(), s);
        ApkManager.getInstance().loadApk(FIRST_APK_KEY, nativeApkPath.getAbsolutePath(), dexOutPath, this);
        handler.sendEmptyMessage(0x78);
    }
}
