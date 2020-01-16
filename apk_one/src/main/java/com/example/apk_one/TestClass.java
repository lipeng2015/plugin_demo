package com.example.apk_one;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.plug_tool.plugin.PluginInterfaceImp;
import com.example.plug_tool.PlugUtils;

public class TestClass extends PluginInterfaceImp {

    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState, final Activity activity) {
        this.activity=activity;

        activity.setContentView(R.layout.activity_main);

        activity.findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlugUtils.goActivity(activity,"first_apk", "two_class");
            }
        });

    }
}
