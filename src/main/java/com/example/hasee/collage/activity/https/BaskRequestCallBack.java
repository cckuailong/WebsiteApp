package com.example.hasee.collage.activity.https;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.hasee.collage.activity.utils.Loading;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hasee on 2016/11/22.
 */

public abstract class BaskRequestCallBack<T> extends RequestCallBack<String> {
    private Type type;
    public BaskRequestCallBack(){
        Type superClass = this.getClass().getGenericSuperclass();
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        Loading.dismiss();
        if(responseInfo != null){
            String json = responseInfo.result;
            if(json.matches("^\\{.*\\}$")){
                Log.i("wgat", "onSuccess: "+json.substring(0,10));
                T t = JSON.parseObject(json,type);
                if(t != null){
                    success(t);
                }else {
                    XUtils.show("无数据");
                }


            }else{
                XUtils.show("获取的数据格式错误");
            }

        }else{
            XUtils.show("数据加载失败！");
        }
    }

    @Override
    public void onFailure(HttpException e, String s) {
        Loading.dismiss();
        XUtils.show("网络请求失败");
        Log.i("error", "onFailure: "+"==============="+s);
        e.printStackTrace();
        failure();
    }
    public abstract void success(T date);
    public void failure(){
    }

}
