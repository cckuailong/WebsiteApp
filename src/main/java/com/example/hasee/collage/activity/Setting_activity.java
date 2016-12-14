package com.example.hasee.collage.activity;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;

public class Setting_activity extends BaseActivity {
    @ViewInject(R.id.setting_cb_mode)
    private CheckBox cbMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        registerReceiver(receiver,new IntentFilter("com.guxu_MODE_CHANGED"));
    }
    private  void init(){
        boolean isNight = SharedPrefUtil.isNight(this);
        if(isNight){
            setTheme(R.style.Night);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_setting);
        ViewUtils.inject(this);
        cbMode.setChecked(isNight);
        Log.i("night", "init: night");
    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context,Setting_activity.class);
        context.startActivity(intent);
    }
    @OnCompoundButtonCheckedChange(R.id.setting_cb_mode)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPrefUtil.updateMode(this,isChecked);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}

