package com.example.hasee.news.Utils;

import android.app.Activity;
import android.content.Context;
import com.example.hasee.news.View.CommonProgressDialog;

/**
 * Created by hasee on 2016/10/21.
 *
 * 弹窗工具类
 */

public class ProgressDialogUtils {
    private CommonProgressDialog dialog;
    private Activity activity;
    public void showProgressDialg(Activity activity, String msg){
        this.activity = activity;

            if(dialog == null){
                dialog = new CommonProgressDialog(activity);
            }
            if(msg == null){
                msg ="正在加载...";
            }
            dialog.setMessage(msg);
            if(!activity.isFinishing() && !dialog.isShowing()){
                dialog.show();
            }
    }
    public void closeProgressDialog() {
        if (dialog != null && !activity.isFinishing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
