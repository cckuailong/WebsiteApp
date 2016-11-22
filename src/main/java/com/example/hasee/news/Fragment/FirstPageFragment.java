package com.example.hasee.news.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.news.Bean.BannerBean;
import com.example.hasee.news.Bean.Data;
import com.example.hasee.news.MsgDetialActivity;
import com.example.hasee.news.R;
import com.example.hasee.news.Utils.JsonUtils;
import com.example.hasee.news.Utils.ProgressDialogUtils;
import com.example.hasee.news.adapter.FirstPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/10/19.
 * 主界面的Fragment
 */

public class FirstPageFragment extends Fragment implements  JsonUtils.CallBackListener,SwipeRefreshLayout.OnRefreshListener {

    private int mPage;
    private  ProgressDialogUtils utils;
    private int now_num = 8;//记录当前存放的数据条数
    private List<Data> item_list;//记录当前真正要显示的数据
    private RecyclerView mrecyclerview;
    private JsonUtils jsonutil;
    private SwipeRefreshLayout swip;
    //存储所有网络数据
    private int mPosition;//记录当前的页面
    private int Number = 7;//记录当前存放的数据条数
    private List<Data>[] msg_list;
    private BannerBean mBannerBean;//存储轮播图对象数据
    private FirstPageAdapter adapter;
    private  int LastVisbileItem;//记录item的最后一个下标

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        getData();

        mrecyclerview = (RecyclerView) view.findViewById(R.id.recycle_view);
        swip = (SwipeRefreshLayout) view.findViewById(R.id.swip);
        swip.setOnRefreshListener(this);
        swip.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_green_light);
        adapter = new FirstPageAdapter(getActivity(),item_list,mBannerBean);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerview.setAdapter(adapter);
        initlistener();
        return view;
    }
    //给上拉刷新实现事件监听
    private void initlistener() {
        mrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager)recyclerView.getLayoutManager();
                LastVisbileItem = lm.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(LastVisbileItem+1 == adapter.getItemCount() && newState == RecyclerView.SCROLL_STATE_IDLE){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            now_num+=4;
                            initdata(2);

                        }
                    },1500);
                }
            }
        });
        adapter.setMyItemClickListener(new FirstPageAdapter.MyitemClickListener(){

            @Override
            public void onClick(View itemView, int position) {
                String url = item_list.get(position-1).getUrl();
                //Log.i("", "onClick: --->"+position);
                Intent intent = new Intent(getActivity(), MsgDetialActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("position",mPosition);
                startActivity(intent);
            }
        });
    }

    private  void getData(){
        //注册本类去监听数据加载状态
        jsonutil = new JsonUtils(this);
        mBannerBean = new BannerBean();
        item_list = new ArrayList<>();
        //启动加载数据方法；
        jsonutil.getResult();

        //utils.showProgressDialg(getActivity(),"loding...");
    }
    //回调的更新界面方法
    @Override
    public void update(List<Data>[] msg_list) {

        //utils.closeProgressDialog();
        this.msg_list = new ArrayList[7];
        this.msg_list = msg_list;

        initdata(1);

    }

    public void initdata(int isrefsh){
        if(msg_list != null) {

            String []img = new String[3];
            String []title = new String[3];
            String []tourl = new String[3];

            List<Data> data = msg_list[0];
            for(int i = 0;i<3;i++){
                Log.i("list22", "update: "+data.get(i).getThumbnail_pic_s());
                img[i] = data.get(i).getThumbnail_pic_s();
                title[i] = data.get(i).getTitle();
                tourl[i] = data.get(i).getUrl();

            }
            mBannerBean.setImg_url(img);
            mBannerBean.setTitle(title);
            mBannerBean.setTourl(tourl);
            if(isrefsh ==1){
                List<Data> item_list2 = new ArrayList<>();
                for(int i = 0;i<item_list.size();i++){
                    Data d = item_list.get(i);
                    item_list2.add(d);
                }
                item_list.clear();
                if(now_num-4 >= 8){
                    for(int i = now_num-4;i<now_num;i++){
                        if(i<data.size())
                            item_list.add(data.get(i));
                        else
                            break;
                    }
                    for(int i = 0;i<item_list2.size();i++){
                        Data da = item_list2.get(i);
                        item_list.add(da);
                    }
                }
                else{
                    for (int i = 3; i < now_num; i++) {
                        if(i<data.size())
                            item_list.add(data.get(i));
                        else
                            break;
                    }
                }
            }
            else{
                item_list.clear();
                for (int i = 3; i < now_num; i++) {
                    if(i<data.size())
                        item_list.add(data.get(i));
                    else
                        break;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
    //下拉刷新方法回调
    @Override
    public void onRefresh() {
        now_num +=4;//刷新操作执行后多显示4条数据
        initdata(1);
        swip.setRefreshing(false);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    //滑动时更新数据
    public void setPositoin(int positoin){
        mPosition = positoin;
        initdata(1);
    }
}
