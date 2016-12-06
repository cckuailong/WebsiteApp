package com.example.hasee.news;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

public class MsgDetialActivity extends AppCompatActivity{
    private WebView webview;
    private Button btn;
    public static final String APP_ID ="wx7204235596f0c3ba";
    public IWXAPI API;
    private static String URL;
    private static String title;
    private static String pic_url;
    private static Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detial);
        webview = (WebView) findViewById(R.id.webView_msgDetail);
        btn = (Button) findViewById(R.id.weixin);
        API = WXAPIFactory.createWXAPI(this,APP_ID);
        //将appid注册到微信中
        API.registerApp(APP_ID);


        //打开页面时自适应屏幕
        WebSettings webSettings = webview.getSettings();
        webSettings.setUseWideViewPort(true);//可根据任意比例缩放
        int position = getIntent().getIntExtra("position",0);

        String url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        pic_url = getIntent().getStringExtra("pic_url");
        String url1 = "";
        char[] a = url.toCharArray();
        for(int i = 0;i<url.length();i++){
            if(a[i] == '?'){
                break;
            }else{
                url1+=a[i];
            }
        }
        Log.i("url", "onCreate: "+url1);
        URL = url1;
        webview.loadUrl(url1);
    }
    //向好友发送文本
    public void onclick_weixin(View view){
        //设置一个缩略图
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = URL;
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = title;
        Log.i("", "run: ========"+title);

        //Bitmap bitmap = BitmapFactory.decodeStream(new URL(pic_url).openStream());
        //msg.thumbData = bmpToByte(bitmap,true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransession("webpage");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        API.sendReq(req);


    }
    public  byte[] bmpToByte(final  Bitmap bitmap,final  boolean need ){
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
        if(need){
            bitmap.recycle();
        }
        byte[] result = out.toByteArray();
        try{
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
    public String buildTransession(final  String type){
        if(type == null){
            return String.valueOf(System.currentTimeMillis());
        }else{
            return String.valueOf(System.currentTimeMillis())+type;
        }
    }
}
