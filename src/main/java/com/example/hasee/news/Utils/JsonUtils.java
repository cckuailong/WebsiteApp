package com.example.hasee.news.Utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.hasee.news.Bean.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
    private ArrayList[] msg_list;
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
                /**
                OkHttpClient okHttpClient = new OkHttpClient();
                //新建一个请求
                Request request = new Request.Builder().url("http://172.20.46.185:8080/app_server/getData?str=1").build();
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
                });**/
                try {
                    String Json = readStream(new java.net.URL("http://v.juhe.cn/toutiao/index?type=top&key=1ce4e176c63e93e0f32ba4b608f6b9b2").openStream());
                    Message msg = new Message();
                    msg.obj = Json;
                    msg.arg1 = 0x1;
                    myHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

        String json = msg;



        Log.i("========", "getJson:"+json);
        initMessageList(json);
    }
    public void initMessageList(String json){
        try {
            JSONObject  OBJ = new JSONObject(json);
            JSONObject RE = OBJ.getJSONObject("result");

            JSONArray data1 = RE.getJSONArray("data");
            msg_list = new ArrayList[7];
            msg_list[0] = new ArrayList();
            msg_list[1] = new ArrayList();
            msg_list[2] = new ArrayList();
            msg_list[3] = new ArrayList();
            msg_list[4] = new ArrayList();
            msg_list[5] = new ArrayList();
            msg_list[6] = new ArrayList();
            for(int i = 0;i<data1.length();i++){
                JSONObject obj = data1.getJSONObject(i);
                Data data = new Data();
                data.setAuthor_name(obj.getString("author_name"));
                data.setDate(obj.getString("date"));
                data.setRealtype(obj.getString("realtype"));
                data.setTitle(obj.getString("title"));
                data.setUrl(obj.getString("url"));
                data.setThumbnail_pic_s(obj.getString("thumbnail_pic_s"));
                switch (data.getRealtype()){
                    case "历史":
                        msg_list[0].add(data);
                        break;
                    case "国内":
                        msg_list[0].add(data);
                        break;
                    case "社会":
                        msg_list[0].add(data);
                        break;
                    case "健康":
                        msg_list[0].add(data);
                        break;
                    case "娱乐":
                        msg_list[0].add(data);
                        break;
                    case "科技":
                        msg_list[0].add(data);
                        break;
                    case "军事":
                        msg_list[0].add(data);
                        break;

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
    private String readStream(InputStream is){


        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is,"utf-8");
            BufferedReader br =new BufferedReader(isr);
            while((line = br.readLine()) != null){
                result+=line;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
