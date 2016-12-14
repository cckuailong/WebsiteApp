package com.example.hasee.collage.activity.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.example.hasee.collage.R;

/**
 * Created by hasee on 2016/11/22.
 */

public class Loading {
    private  static Dialog dialog;
    private static Context mcontext;
    public static  void init(Context context){
        mcontext = context;
    }

    public static void show(){
        if(dialog == null){
            dialog = new AlertDialog.Builder(mcontext).create();
            dialog.setCanceledOnTouchOutside(false);
            Window w = dialog.getWindow();
            w.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();
            w.setContentView(R.layout.lauout_dialog);


        }else{
            dialog.show();
        }
    }
    public  static void dismiss(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
    public static boolean isShowing(){
        if(dialog != null){
            return dialog.isShowing();
        }
        return false;
    }
    public static void destory(){
        dismiss();
        if(dialog != null){
            dialog = null;
            mcontext = null;
        }
    }
}
