package com.example.hasee.collage.activity.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hasee on 2016/12/3.
 */

public class NetWorkUtil {

    public static final int NONE = -1;
    public static final int WIFI = ConnectivityManager.TYPE_WIFI;
    public static final int MOBLE = ConnectivityManager.TYPE_MOBILE;
    public static int getNetWorkMode(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if( info != null && info.isAvailable()){
            return info.getType();
        }else{
            return NONE;
        }
    }

}
