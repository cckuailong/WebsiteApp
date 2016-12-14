package com.example.hasee.collage.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Result;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.BaskRequestCallBack;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import view.MEditText;
import view.TitleView;

public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.login_account)
    private MEditText mAccount;
    @ViewInject(R.id.login_password)
    private MEditText mePwd;
    @ViewInject(R.id.login_title)
    private TitleView title;
    @ViewInject(R.id.login_sign_in)
    private Button btSignIn;
    private String pwd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        registerReceiver(receiver,new IntentFilter("com.guxu_MODE_CHANGED"));

    }

    private View.OnClickListener clicklis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_sign_in:
                    login();
                    break;
                case R.id.title_back:
                    finish();
                    break;
                case R.id.title_tv_right:
                    break;
            }
        }
    };

    @OnClick({R.id.sign_up, R.id.login_resrt_pwd})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up:
                Intent intent = new Intent(this,validate.class);
                intent.putExtra("isReg",true);
                startActivity(intent);
                break;
            case R.id.login_resrt_pwd:
                Intent intent2 = new Intent(this,validate.class);
                intent2.putExtra("isReg",false);
                startActivity(intent2);
                break;
        }
    }

    private void login() {
        String account = mAccount.getText();
        pwd = mePwd.getText();
        RequestParams params = new RequestParams();
        if (account.matches("^1(3|4|5|7|8)\\d{9}$")) {
            params.addBodyParameter("u.id.phone", account);
        } else if (account.matches("^\\w+@\\w+\\.(com|cn)(.cn)?$")) {
            params.addBodyParameter("u.id.email", account);
        } else {
            Toast.makeText(this, "账号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.matches("^\\w{6,20}$")) {
            Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        params.addBodyParameter("u.id.pwd",pwd);
        XUtils.send(XUtils.login,params, new BaskRequestCallBack<Result<UserId>>() {

            @Override
            public void success(Result<UserId> date) {
                if (date.state == Result.STA_SUC) {
                    UserId u = date.data;
                    u.setPwd(pwd);
                    if(((MyApp) getApplication()).getUser() != null){
                        String pw = ((MyApp) getApplication()).getUser().getPwd();
                        String ac = ((MyApp) getApplication()).getUser().getPhone();
                        Log.i("login", "success: "+((MyApp) getApplication()).getUser().getGender());
                        Log.i("login", "success: "+((MyApp) getApplication()).getUser().getPhone());
                        Log.i("login", "success: "+((MyApp) getApplication()).getUser().getPwd());
                        if(u.getPhone().matches(ac) ){

                        }else{
                            ((MyApp) getApplication()).setUser(u);
                        }
                    }else{
                        ((MyApp) getApplication()).setUser(u);
                    }


                    //Log.i("Test", "success: "+((MyApp) getApplication()).getUser().getPhone());
                    ((MyApp) getApplication()).setRegister(true);
                    //跳转
                    if (getIntent().getBooleanExtra("isToCenter", true)) {
                        CenterActivity.startActivity(LoginActivity.this);
                    }
                    sendBroadcast(new Intent("com.guxu.Login_complicate"));
                    finish();

                }else{
                    XUtils.show("账号或密码错误");
                }
            }

        }, true);

    }
    private void init(){
        boolean isNight = SharedPrefUtil.isNight(this);
        if(isNight){
            setTheme(R.style.Night);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);

        title.setBackClickListener(clicklis);
        title.setRightClickListener(clicklis);
        btSignIn.setOnClickListener(clicklis);
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
    public static void startActivity(Context context,boolean isToCenter){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtra("isToCenter",isToCenter);
        context.startActivity(intent);
    }
}
