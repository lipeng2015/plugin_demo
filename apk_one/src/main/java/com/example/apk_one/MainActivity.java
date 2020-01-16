package com.example.apk_one;

import com.example.plug_tool.plugin.PluginInterface;
import com.example.plug_tool.plugin.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public PluginInterface getProxyBase() {
        return new TestClass();
    }
}
