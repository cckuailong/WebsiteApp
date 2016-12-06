package com.example.hasee.news.Bean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by hasee on 2016/12/3.
 */

public class SharedPrefUtil {
    private static final String NAME = "college.spf";
    public static void updateMode(Context context,boolean isNight){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
       if( sp.getBoolean("isNight",false) != isNight ){
           SharedPreferences.Editor e = sp.edit();
           e.putBoolean("isNight",isNight);
           e.commit();
           context.sendBroadcast(new Intent("com.guxu_MODE_CHANGED"));

       }

    }
    public static  boolean isNight(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean("isNight",false);

    }
}
