package com.example.hasee.collage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Data1;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.saveNews;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import view.TitleView;

public class NewsInfoActivity extends BaseActivity {
    private TitleView title;
    private WebView webView;
    private String appSecret = "4c94c8abd6b40cecacde28ac9657d95d";
    private String appId = "wxa2dff7524423bb83";
    private ProgressBar pg1;
    private String url;
    private String texttitle;
    private String picUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        url = getIntent().getStringExtra("news");
        texttitle = getIntent().getStringExtra("title");
        picUrl = getIntent().getStringExtra("picUrl");
        init();
        registerReceiver(receiver,new IntentFilter("com.guxu_MODE_CHANGED"));
    }
    private void init() {
        boolean isNight = SharedPrefUtil.isNight(this);
        if(isNight){
            setTheme(R.style.Night);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_newsinfo);
        title = (TitleView) findViewById(R.id.info_title);
        webView=(WebView) findViewById(R.id.web);
        pg1=(ProgressBar) findViewById(R.id.progressBar1);
        if(url!=null){
            webView.loadUrl(url);
        }
        title.setRightClickListener(clickListener);
        title.setBackClickListener(clickListener);

        // TODO 自动生成的方法存根
        webView.setWebViewClient(new WebViewClient(){
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings seting=webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        webView.setOnLongClickListener(clickListener2);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if(newProgress==100){
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }

            }
        });

    }
    private View.OnLongClickListener clickListener2 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Log.i("longClick", "onLongClick: ");
            showGender();
            return true;
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_img_right:
                    CenterActivity.startActivity(NewsInfoActivity.this);
                    break;
                case R.id.title_back:
                    finish();
            }
        }
    };
    public static void startActivity(Context context, String news,String title,String picUrl){
        Intent in = new Intent(context,NewsInfoActivity.class);
        in.putExtra("news",news);
        in.putExtra("title",title);
        in.putExtra("picUrl",picUrl);

        context.startActivity(in);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            }
            else {//当webview处于第一页面时,直接退出程序
                finish();
            }
        }
        if(keyCode == KeyEvent.KEYCODE_MENU){
            sharee();
        }
        //Log.i("key", "onKeyDown: "+event);
        return super.onKeyDown(keyCode, event);
    }
    private void sharee(){
        OnekeyShare share = new OnekeyShare();
        share.setImageUrl(picUrl);
        share.setTitleUrl(url);
        share.setText(texttitle);
        share.setSite(texttitle);
        share.setSiteUrl(url);
        share.setTitle(texttitle);
        share.setUrl(url);
        //share.setPlatform("HITNEW");
        share.show(this);
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
    private void showGender(){
        //Log.i("======", "showGender: dknvkdnvkdnvknvkd");
        new AlertDialog.Builder(this).setSingleChoiceItems(new String[]{"收藏新闻","分享新闻"},1,dialogClickd).show();
    }
    private DialogInterface.OnClickListener dialogClickd = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){
                case 1:
                    dialog.dismiss();
                    sharee();
                    break;
                case 0:
                    dialog.dismiss();
                    savenew();
                    break;
            }
        }
    };
    private void savenew(){
        //Log.i("save", "savenew: "+texttitle+"=="+url);
        saveNews news = new saveNews();
        Calendar now = new GregorianCalendar();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String filename = simpleDate.format(now.getTime());
        news.setUrl(url);
        news.setTitle(texttitle);
        news.setPicUrl(picUrl);
        news.setNid(filename);
        news.setAccount("111111");
        List<saveNews> list = new ArrayList<>();
        if(XUtils.getnew()!= null && XUtils.getnew().size()>0){
            list = XUtils.getnew();
            list.add(news);
        }else{
            list.add(news);
        }

        XUtils.save(list);
        XUtils.show("收藏成功");
        //Log.i("save", "savenew: "+si);
        //Log.i("save", "savenew: "+XUtils.getSaveNews(((MyApp)getApplication()).getUser().getPhone()).get(si-1).getTitle()+
              // XUtils.getSaveNews(((MyApp)getApplication()).getUser().getPhone()).get(si-1).getUrl());
    }
}
