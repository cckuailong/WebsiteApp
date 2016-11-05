package com.example.hasee.news.View;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.hasee.news.R;

/**
 * Created by hasee on 2016/10/21.
 */

public class CommonProgressDialog extends Dialog {

    public CommonProgressDialog(Context context) {
        super(context);

        setContentView(R.layout.commonprogressdialog);

        //显示在屏幕中间
        getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    /*
   设置加载消息的方法
    */
    public void setMessage(String msg){
        TextView textView = (TextView) findViewById(R.id.id_lodingText);
        textView.setText(msg);

    }
}
