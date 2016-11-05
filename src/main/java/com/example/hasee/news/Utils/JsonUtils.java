package com.example.hasee.news.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.hasee.news.Bean.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by hasee on 2016/10/25.
 */

public class JsonUtils {

    //访问路径
    private List<Data>[] msg_list;
    //返回的数据格式[[{}]]
    //获取网络数据
    private CallBackListener listener;
    public JsonUtils(){

    }
    public JsonUtils(CallBackListener listener){
        this.listener = listener;
    }
    public void getResult(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //需要创建一个okhttp client对象 一个app里面只实例化一个对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //新建一个请求
                Request request = new Request.Builder().url("http://news.ifeng.com/").build();
                //执行一个请求，获得网络数据
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String str = response.body().string();
                            Message msg = new Message();
                            msg.obj = str;
                            msg.arg1 = 0x1;
                            myHandler.sendMessage(msg);
                        }
                    }
                });

            }
        }).start();
    }
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 0x1){
                //处理返回的字符串
                getJson(msg.obj.toString());
            }
        }
    };
    //f返回截取的json数据
    public void getJson(String msg){
        String json = null;
        if(msg != null){
            json = msg.substring(msg.indexOf("[[{"),msg.indexOf("}]]")+3);
            //Log.i("Tag", "getJson: "+json);
        }
        initMessageList(json);
    }
    public void initMessageList(String json){
        try {
            JSONArray array = new JSONArray(json);

            msg_list = new ArrayList[array.length()];
            for(int i = 0;i<array.length();i++){
                JSONArray arr = array.getJSONArray(i);
                msg_list[i] = new ArrayList<>();
                for(int j = 0;j<arr.length();j++){
                   JSONObject obj = arr.getJSONObject(j);
                    Data data = new Data();
                    data.setThumbnail(obj.getString("thumbnail"));
                    data.setTitle(obj.getString("title"));
                    data.setUrl(obj.getString("url"));
                    msg_list[i].add(data);
                }
            }
           // Log.i("Tag", "getJson: "+msg_list[1]
                   // .get(1).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //通知注册进来的类去更新界面
        listener.update(msg_list);
    }
    //关注着数据加载类的父类接口
    public interface  CallBackListener{
        void update(List<Data>[] msg_list);
    }

}
