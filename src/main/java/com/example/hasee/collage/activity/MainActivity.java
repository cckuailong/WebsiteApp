package com.example.hasee.collage.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Result;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.PageFragment.PageFragment;
import com.example.hasee.collage.activity.auxiliary.ActivityController;
import com.example.hasee.collage.activity.auxiliary.Code;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.BaskRequestCallBack;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

import view.TitleView;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.main_title)
    private TitleView title;
    @ViewInject(R.id.main_idictor)
    private TabPageIndicator tabPageIndicator;
    @ViewInject(R.id.main_content)
    private ViewPager pager;
    @ViewInject(R.id.main_add_tab)
    private ImageButton btAdd;
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        registerReceiver(receiver,new IntentFilter("com.guxu_MODE_CHANGED"));
        //registerReceiver(receiver1,new IntentFilter("com.guxu.head_changed"));

    }

    private View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_img_right:
                    if(((MyApp)getApplication()).getUser() == null){
                        ((MyApp)getApplication()).setRegister(false);
                        LoginActivity.startActivity(MainActivity.this,true);
                    }else{
                        if( ((MyApp)getApplication()).isRegister()== true){
                            CenterActivity.startActivity(MainActivity.this);
                        }else{
                            ((MyApp)getApplication()).setRegister(false);
                            RequestParams params = new RequestParams();
                            UserId u = ((MyApp)getApplication()).getUser();
                            params.addBodyParameter("u.id.phone",u.getPhone());
                            params.addBodyParameter("u.id.pwd",u.getPwd());
                            XUtils.send(XUtils.login, params, new BaskRequestCallBack<Result<UserId>>() {
                                @Override
                                public void success(Result<UserId> date) {
                                    if(date.state == Result.STA_SUC){
                                        CenterActivity.startActivity(MainActivity.this);
                                        ((MyApp)getApplication()).setRegister(true);
                                    }else{
                                        LoginActivity.startActivity(MainActivity.this,true);
                                    }
                                }

                                @Override
                                public void onFailure(HttpException e, String s) {
                                    super.onFailure(e, s);
                                    LoginActivity.startActivity(MainActivity.this,true);
                                }
                            },true);
                        }
                    }
                    break;
                case R.id.main_add_tab:
                    SelectTabActivity.startActivityForResult(MainActivity.this);
                    break;
            }
        }
    };
    private void init(){
        boolean isNight = SharedPrefUtil.isNight(this);
        if(isNight){
            setTheme(R.style.Night);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        title.setRightClickListener(clicks);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabPageIndicator.setViewPager(pager);
        btAdd.setOnClickListener(clicks);
        //registerReceiver(receiver1,new IntentFilter("com.guxu.Login_complicate"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Code.REQ_SELECT_TAB && resultCode == Code.RESP_SELECT_TAB){
            tabPageIndicator.notifyDataSetChanged();
            pager.getAdapter().notifyDataSetChanged();
        }
    }

    class PageAdapter extends FragmentPagerAdapter{


        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PageFragment fragment = new PageFragment();
            fragment.setTab(((MyApp)getApplication()).getMyTabs().get(position).getTab());
            return fragment;
        }

        @Override
        public int getCount() {
            return ((MyApp)getApplication()).getMyTabs().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ((MyApp)getApplication()).getMyTabs().get(position).getTab();
        }
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };
/**
    private BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };**/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver1);
        unregisterReceiver(receiver);
    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityController.closeAllActivity();
            System.exit(0);
        }
    }

}
