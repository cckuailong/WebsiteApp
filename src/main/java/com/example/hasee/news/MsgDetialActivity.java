package com.example.hasee.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MsgDetialActivity extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detial);
        webview = (WebView) findViewById(R.id.webView_msgDetail);
        //打开页面时自适应屏幕
        WebSettings webSettings = webview.getSettings();
        webSettings.setUseWideViewPort(true);//可根据任意比例缩放
        int position = getIntent().getIntExtra("position",0);

        String url = getIntent().getStringExtra("url");
        webview.loadUrl(url);
    }


}
