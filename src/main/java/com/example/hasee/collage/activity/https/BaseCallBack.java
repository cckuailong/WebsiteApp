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
 * Created by hasee on 2016/12/10.
 */

public abstract class BaseCallBack<String> extends RequestCallBack<String> {
    public BaseCallBack(){
        Type superClass = this.getClass().getGenericSuperclass();
    }

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        Loading.dismiss();
        if(responseInfo != null){
            String json = responseInfo.result;
            success(json);


        }else{
            XUtils.show("数据加载失败！");
        }
    }

    @Override
    public void onFailure(HttpException e, java.lang.String s) {

    }

    public abstract void success(String date);
    public void failure(){
    }

}
