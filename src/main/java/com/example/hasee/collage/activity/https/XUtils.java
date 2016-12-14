package com.example.hasee.collage.activity.https;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Data1;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.Tab;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.Bean.saveNews;
import com.example.hasee.collage.activity.utils.Loading;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hasee on 2016/11/4.
 */

public class XUtils {
    public static String U = "http://101.200.61.191/app_server/";
    private static BitmapUtils bitmapUtils;
    public static final String login = "login";
    public static final String REG = "register";
    public static final String TABS = "getData?str=1";
    public static final String UPDATE = "update";
    private static Context mcontext;
    private static DbUtils dbUtils;
    private static HttpUtils httpUtils;
    private static HttpUtils h;
    private static HttpHandler handler;
    public  static  void init(Context context){
        mcontext = context;
        if(bitmapUtils == null){
            bitmapUtils = new BitmapUtils(context);
            bitmapUtils.configDefaultLoadingImage(R.drawable.no_data);
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.no_data);
            bitmapUtils.configDiskCacheEnabled(true);

        }
        if(httpUtils == null){
            httpUtils = new HttpUtils();
        }
        if(dbUtils == null){
            dbUtils = DbUtils.create(context,"college.db");
        }
    }
    public static <T> void send(String url, RequestCallBack<T> callBack,boolean loading){
        send(url,null,callBack,loading);
    }
    public static <T> void send(String url, RequestParams params, RequestCallBack<T> callBack,boolean loading){
        if(loading){
            Loading.show();
        }
        if(params == null){
           handler =  httpUtils.send(HttpRequest.HttpMethod.GET,U+url,callBack);
        }else {
            handler = httpUtils.send(HttpRequest.HttpMethod.POST,U+url,params,callBack);
        }
    }
    public static void cancle(){
        if(handler != null){
            handler.cancel();
            handler = null;
        }
    }
    public static void display(ImageView img,String url){

        bitmapUtils.display(img,url);
    }
    public static void show(String text){
        Toast.makeText(mcontext,text,Toast.LENGTH_SHORT).show();

    }
    public static void show(int text){
        Toast.makeText(mcontext,text,Toast.LENGTH_SHORT).show();

    }
    public static void saveTabs(List<Tab> tabs){

        try {
            List<Tab> other = findAll("1");
            try{
                dbUtils.delete(Tab.class);
            }catch (Exception e){
                Log.i("college", "saveTabs: =======数据为空===");
            }
            if(other != null){
                for(Tab t:tabs){
                    if(other.contains(t)){
                        Log.i("Tag", "saveTabs: "+"jdsncjndjvn");
                        t.setIsmy(1);
                    }
                }
            }
            dbUtils.saveAll(tabs);
            List<Tab> my = dbUtils.findAll(Selector.from(Tab.class));
            Log.i("accccc", "saveTabs: "+my.size());
        } catch (DbException e) {
            e.printStackTrace();
        }


    }
    public static List<Tab> findAll(String ismyValue){
        try {
            List<Tab> l = dbUtils.findAll(Selector.from(Tab.class).where("ismy","=",ismyValue));
            if(l!=null && l.size()>0){
                return l;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
    public static void updateTabs(List<Tab> tabs){
        try {
            dbUtils.updateAll(tabs);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static UserId loadUserId() {
        try {
            return dbUtils.findFirst(UserId.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void saveUserId(UserId u) {
        try {
            dbUtils.deleteAll(UserId.class);
            dbUtils.save(u);
            Log.i("save", "saveUser: "+XUtils.loadUserId().getGender());
        } catch (DbException e){
            e.printStackTrace();
        }
    }
    public static void saveListTab(List<Tab> tabs)  {
        if(tabs != null && tabs.size()>0){
            try {
                dbUtils.deleteAll(Tab.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
            for(Tab tab:tabs){
                try {
                    dbUtils.save(tab);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void saveNews(List<DataId>list){
        if(list != null && list.size()>0){
            try {
                dbUtils.deleteAll(DataId.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
            for(DataId da:list){
                try {
                    dbUtils.save(da);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static List<DataId> findNews(){
        try {
            List<DataId> l = dbUtils.findAll(Selector.from(DataId.class));
            if(l!=null && l.size()>0){
                return l;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new LinkedList<DataId>();
    }
    public static List<DataId> findtypeNews(String type){
        try {
            List<DataId> l = dbUtils.findAll(Selector.from(DataId.class).where("type","=",type));
            if(l!=null && l.size()>0){
                return l;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new LinkedList<DataId>();
    }
    public static void save(List<saveNews> list){
        try {
            dbUtils.deleteAll(saveNews.class);
            try {
                dbUtils.saveAll(list);
                Log.i("save", "save: "+list.get(list.size()-1).getTitle());
            } catch (DbException e) {
                e.printStackTrace();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
    public static List<saveNews> getnew(){
        List<saveNews>list = new ArrayList<>();
        try {
            list = dbUtils.findAll(saveNews.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

}
