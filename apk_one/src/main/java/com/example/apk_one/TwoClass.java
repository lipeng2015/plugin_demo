package com.example.apk_one;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.plug_tool.plugin.PluginInterfaceImp;

public class TwoClass extends PluginInterfaceImp {

    @Override
    public void onCreate(Bundle savedInstanceState, Activity activity) {
        super.onCreate(savedInstanceState, activity);

        activity.setContentView(R.layout.activity_main);

        TextView showFont= (TextView) activity.findViewById(R.id.showFont);
        activity.findViewById(R.id.jump).setVisibility(View.GONE);

        showFont.setText("当前是module_apk_one的two_class");
    }
}
