package com.example.hasee.collage.activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.https.BaseCallBack;
import com.example.hasee.collage.activity.https.XUtils;

public class WeatherActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        XUtils.send("http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=222ab9928c6579ab9a90b89efffebdc2", new BaseCallBack<String>() {
            @Override
            public void success(String date) {
                Log.i("aaaa", "success: "+date);
            }
        },true);
    }
}
