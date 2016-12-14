package com.example.hasee.collage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.auxiliary.ActivityController;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;

public class BaseActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActivityController.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityController.removeActivity(this);
    }



}
