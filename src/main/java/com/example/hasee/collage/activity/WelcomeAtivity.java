package com.example.hasee.collage.activity;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.ListTab;
import com.example.hasee.collage.activity.Bean.Result;
import com.example.hasee.collage.activity.Bean.Tab;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.BaseCallBack;
import com.example.hasee.collage.activity.https.BaskRequestCallBack;
import com.example.hasee.collage.activity.https.XUtils;
import com.example.hasee.collage.activity.utils.NetWorkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WelcomeAtivity extends BaseActivity {
    private Handler handler = new Handler();
    private boolean isCan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_ativity);
        Log.i("what", "onCreate: "+NetWorkUtil.getNetWorkMode(this));
        if(NetWorkUtil.getNetWorkMode(this)!= NetWorkUtil.NONE  && NetWorkUtil.getNetWorkMode(this)!=NetWorkUtil.MOBLE){
            XUtils.send(XUtils.TABS, new BaseCallBack<String>() {

                @Override
                public void success(String date) {
                    try {
                        JSONObject  obj = new JSONObject(date);
                        JSONArray array = obj.getJSONArray("data");
                        Log.i("whata", "success: "+array.length());
                        List<DataId> list = new ArrayList<DataId>();
                        for(int i = 0;i<array.length();i++){
                            JSONObject o = array.getJSONObject(i);
                            DataId n = new DataId();
                            n.setAuthorName(o.getString("authorName"));
                            n.setDate(o.getString("date"));
                            n.setSource(o.getString("source"));
                            n.setThumbnailPicS(o.getString("thumbnailPicS"));
                            n.setTitle(o.getString("title"));
                            n.setType(o.getString("type"));
                            n.setUniquekey(o.getString("uniquekey"));
                            n.setUrl(o.getString("url"));
                            list.add(n);

                        }
                       // Log.i("whata", "sss: "+list.size());
                        XUtils.saveNews(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   // loadDB();
                }
                @Override
                public void failure() {
                    loadDB();
                }
            },false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.startActivity(WelcomeAtivity.this);
                    finish();
                }
            },500);

        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.startActivity(WelcomeAtivity.this);
                    finish();
                }
            },500);
            Log.i("wocao", "onCreate: "+"wocao");

        }
    }
    private void loadDB(){
        if(havaDate()){
            toMain();
        }else{
            XUtils.show("您已经进入没有网络的异次元");
            finish();
        }
    }
    private boolean havaDate(){
       if(((MyApp)getApplication()).getMyTabs()!=null || ((MyApp)getApplication()).getOtherTabs()!=null){
           return true;
       }else{
           return false;
       }
    }
    private void toMain(){
        if(isCan == true){
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }else{
            isCan = true;
        }

    }

}
