package com.pluggable;

import com.example.plug_tool.HostProxyActivity;

public class HackActivity extends HostProxyActivity {
    @Override
    public String getApkKeyName() {
        return MainActivity.FIRST_APK_KEY;
    }

    @Override
    public String getClassTag() {
        return null;
    }
}
