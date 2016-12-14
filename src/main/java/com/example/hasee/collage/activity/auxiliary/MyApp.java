package com.example.hasee.collage.activity.auxiliary;

import android.app.Application;
import android.util.Log;

import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.ListTab;
import com.example.hasee.collage.activity.Bean.Tab;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.https.XUtils;
import com.example.hasee.collage.activity.utils.Loading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/11/22.
 */

public class MyApp extends Application {
    private UserId user;
    private List<Tab> myTabs = new ListTab().getList();
    private List<Tab> otherTabs;
    private boolean isRegister = false;
    private List<DataId> news = new ArrayList<>();

    public List<DataId> getNews() {
        return news;
    }

    public void setNews(List<DataId> news) {
        this.news = news;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Loading.init(this);
        XUtils.init(this);

    }
    public List<Tab> getMyTabs() {
        if(XUtils.findAll("0")!= null && XUtils.findAll("0").size()>0){
            myTabs = XUtils.findAll("0");
            Log.i("Tab", "getMyTabs: "+XUtils.findAll("0").size());

        }else{
            XUtils.saveTabs(myTabs);
        }
        return myTabs;
    }

    public void setMyTabs(List<Tab> myTabs) {
        this.myTabs = myTabs;
    }

    public List<Tab> getOtherTabs() {
        if(XUtils.findAll("1") != null){
            otherTabs = XUtils.findAll("1");
        }
        return otherTabs;
    }

    public void setOtherTabs(List<Tab> otherTabs) {
        this.otherTabs = otherTabs;
    }

    public UserId getUser() {
        if(user == null){
            return XUtils.loadUserId();
        }
        //UserId u = XUtils.loadUserId();
//        Log.i("load", "getUser: "+u.getPhone());
        //if(u == null){
            //return null;
        //}
        return this.user;
    }

    public void setUser(UserId user) {
        this.user = user;
        XUtils.saveUserId(user);
    }
}
