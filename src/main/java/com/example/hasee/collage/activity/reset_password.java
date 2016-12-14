package com.example.hasee.collage.activity;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Result;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.auxiliary.Code;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.BaseCallBack;
import com.example.hasee.collage.activity.https.BaskRequestCallBack;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;

import view.MEditText;
import view.TitleView;

public class reset_password extends BaseActivity {

    public static void startActivity(Context context,String phone){
        Intent intent = new Intent(context,reset_password.class);
        intent.putExtra("phone",phone);
        context.startActivity(intent);
    }
    @ViewInject(R.id.reset_title)
    private TitleView title;
    @ViewInject(R.id.reset_password1)
    private MEditText memail;
    @ViewInject(R.id.reset_password2)
    private MEditText mePwd;
    @ViewInject(R.id.reset_password3)
    private MEditText meRepwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ViewUtils.inject(this);
        title.setBackClickListener(click);
        title.setRightClickListener(click);
    }
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_back:
                    finish();
                    break;
                case R.id.title_tv_right:
                    signUp();

                    break;
            }
        }
    };
    private void signUp(){
        String phone = getIntent().getStringExtra("phone");
        String email = memail.getText();
        String pwd = mePwd.getText();
        String repwd = meRepwd.getText();
        if(phone == null){
            XUtils.show("发生错误");
            return;
        }
        if(!email.matches("^\\w{6,20}$")){
            XUtils.show("密码格式错误");
            return;
        }
        if(!pwd.matches("^\\w{6,20}$")){
            XUtils.show("密码格式错误");
            return;
        }
        if(pwd.length() != repwd.length()){

            XUtils.show("两次密码不一致");
            return;
        }
        UserId userId = ((MyApp)getApplication()).getUser();
        String nick = userId.getNickname();
        String nic1k = Base64.encodeToString(nick.getBytes(),1);
        String ppp = ((MyApp)getApplication()).getUser().getPwd();
        if(!ppp.matches(email)){
            XUtils.show("旧密码错误");
            return;
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("u.id.phone",phone);
        params.addBodyParameter("u.id.pwd",pwd);
        params.addBodyParameter("u.id.nickname",nic1k);
        //params.addBodyParameter("u.id.email",email);
        Log.i("--------->", "signUp: "+"==="+phone+"===="+email+"+++"+"pwd");
        XUtils.send(XUtils.UPDATE, params, new BaseCallBack<String>() {

            @Override
            public void success(String date) {
                XUtils.show("修改成功");
            }
        },true);
    }

}
