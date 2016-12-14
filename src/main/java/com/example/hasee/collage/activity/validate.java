package com.example.hasee.collage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.auxiliary.Code;
import com.example.hasee.collage.activity.auxiliary.CodeTimerTask;
import com.example.hasee.collage.activity.auxiliary.MyTextWatcher;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;
import com.example.hasee.collage.activity.utils.Loading;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import view.MEditText;
import view.TitleView;

public class validate extends BaseActivity {
    private static String phone;
    @ViewInject(R.id.validate_title)
    private TitleView title;
    @ViewInject(R.id.validate_phone)
    private MEditText mphone;
    @ViewInject(R.id.validate_code)
    private MEditText mcode;
    @ViewInject(R.id.validate_get_code)
    private TextView tvGetCode;
    private Handler had = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    XUtils.show((String)msg.obj);
                    break;
                case 1:
                    XUtils.show(msg.arg1);
                    if(msg.arg2 ==1){
                        CodeTimerTask.getInstence().cancle();
                    }
                    break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMSSDK.initSDK(this,"197be530b3581", "2ef214995d8c951293534ed071c78d65");
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
        setContentView(R.layout.activity_validate);
        ViewUtils.inject(this);
        //title.setBackClickListener(click);
        title.setOnClickListener(click);
        title.setRightClickListener(click);
        mphone.addTextChangedListener(phoneWatcher);
        mcode.addTextChangedListener(codeWatcher);
        tvGetCode.setOnClickListener(click);
        SMSSDK.registerEventHandler(handler);
        if(phone != null){
            mphone.setText(phone);

        }
        if(CodeTimerTask.getInstence().isRun()){
            CodeTimerTask.getInstence().startTimer(tvGetCode);

        }

    }
    private EventHandler handler= new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object o) {
            if(result == SMSSDK.RESULT_COMPLETE){//成功
                switch (event){
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        had.sendMessage(had.obtainMessage(1,R.string.code_send_success,0));
                        break;
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        Loading.dismiss();
                        if(getIntent().getBooleanExtra("isReg",true)){
                            register.startActivity(validate.this,phone);
                        }else{
                            reset_password.startActivity(validate.this,phone);
                        }
                        finish();
                        break;
                }
            }else{//失败
                Log.i("error", "afterEvent:====== "+ JSON.toJSONString(o));
                switch (event){
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        had.sendMessage(had.obtainMessage(1,R.string.code_send_failer,1));
                        Loading.dismiss();
                        break;
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        had.sendMessage(had.obtainMessage(1,R.string.validate_failer,0));
                        Loading.dismiss();

                        break;
                }
            }
        }
    };
    private MyTextWatcher codeWatcher = new MyTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() == 4){
                title.setRightTextVisibility(View.VISIBLE);
            }else{
                title.setRightTextVisibility(View.GONE);
            }
        }
    };

    private MyTextWatcher phoneWatcher = new MyTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().matches(Code.Phone_match)){
                tvGetCode.setEnabled(true);
            }else{
                tvGetCode.setEnabled(false);
            }
        }
    };
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_back:
                    finish();
                    break;
                case R.id.validate_get_code:
                    getCode();
                    break;
                case R.id.title_tv_right:
                    phone = mphone.getText();
                    String code = mcode.getText();
                    if(!phone.matches(Code.Phone_match)){
                        XUtils.show("手机号格式错误");
                        return;
                    }
                    Loading.show();
                    SMSSDK.submitVerificationCode("86",phone,code);
                    reset_password.startActivity(validate.this,phone);
                    break;

            }
        }
    };
    private void getCode(){
        phone = mphone.getText();
        if(phone.matches(Code.Phone_match)){
            SMSSDK.getVerificationCode("86",phone);
            CodeTimerTask.getInstence().startTimer(tvGetCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        SMSSDK.unregisterEventHandler(handler);
    }
    public static void startActivity(Context context,boolean isReg){
        Intent intent = new Intent(context,validate.class);
        intent.putExtra("isReg",isReg);
        context.startActivity(intent);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };

}
