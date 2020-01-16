package com.example.plug_tool.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface PluginInterface {

    void onCreate(Bundle savedInstanceState, Activity activity);

    void onDestroy();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onBackPressed();

    void onSaveInstanceState(Bundle outState);
}
