package com.example.hasee.collage.activity.PageFragment;

import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.Tab;
import com.example.hasee.collage.activity.NewsInfoActivity;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.BaseCallBack;
import com.example.hasee.collage.activity.https.XUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import view.MyViewPager;

/**
 * Created by hasee on 2016/12/6.
 */

public class PageFragment extends Fragment{
    private View v;
    private PullToRefreshListView lv;
    //private Tab tab;
    private String tab;
    private View header;
    private ImageView imgNone;
    private List<DataId> headerNews;
    private List<DataId> otherNews;
    private TextView headertitle;
    private MyViewPager vpheader;
    private boolean isMove;
    private int page = 1;
    private int lastPage;
    private CirclePageIndicator indicator;
    private Handler handler = new Handler();
    private OtherAdapter otherAdapter;
    private Handler ha = new Handler();
    private boolean reflash = false;
    private int num = 10;

    public void setTab(String tab){
        this.tab = tab;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //tab = new Tab();
        //tab = (Tab)getArguments().getSerializable("tab");
        if(v==null){
            headerNews = new ArrayList<>();
            otherNews = new ArrayList<>();
            v = inflater.inflate(R.layout.layout_page,null);
            lv = (PullToRefreshListView) v.findViewById(R.id.page_listview);
            imgNone = (ImageView) v.findViewById(R.id.page_none);
            loadTitle();
            lv.getRefreshableView().addHeaderView(header);
            otherAdapter = new OtherAdapter();
            lv.setAdapter(otherAdapter);
            lv.setOnRefreshListener(refreshListener2);
            lv.setOnItemClickListener(itemClickListener);
            imgNone.setOnClickListener(clicks);
            loadData1(true);
        }
        ViewGroup parent = (ViewGroup)v.getParent();
        if(parent != null){
            parent.removeView(v);
        }
        return v;
    }
    private View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadData(true,2);
        }
    };
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataId news = otherNews.get(position-2);
            NewsInfoActivity.startActivity(getActivity(),news.getUrl(),news.getTitle(),news.getThumbnailPicS());
        }
    };
    private PullToRefreshBase.OnRefreshListener2 refreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            num+=4;
            lastPage = page;
            page = 1;
            loadData(true,1);


        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            num+=4;
            loadData(true,2);

        }
    };
    private void loadData(final boolean isRefresh, final int down){
        XUtils.send(XUtils.TABS, new BaseCallBack<String>() {
            @Override
            public void success(String date) {
                lv.onRefreshComplete();
                try {
                    JSONObject obj = new JSONObject(date);
                    String des = obj.getString("state");
                    //Log.i("whata", "success: "+des);
                    //Log.i("whata", "success: "+date.substring(1,100));
                    if(des.matches("1")){
                        List<DataId> list1 = new ArrayList<DataId>();
                        List<DataId> list2 = new ArrayList<DataId>();
                        JSONArray array = obj.getJSONArray("data");
                        //Log.i("whata", "success: "+array.length());
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
                        if(isRefresh && tab!=null){

                            for(int i = 0;i<list.size();i++){
                                DataId da = list.get(i);
                                if(da.getType().matches(tab)){
                                    list1.add(da);
                                }
                            }
                            if(down==1){
                                if(list1.size()>num){
                                    for(int i=num-4;i<num;i++){
                                        list2.add(list1.get(i));
                                    }
                                    for(int i=0;i<num-4;i++){
                                        list2.add(list1.get(i));
                                    }
                                }else{
                                    for(int i=0;i<list1.size();i++){
                                        list2.add(list1.get(i));
                                    }

                                }
                            }
                            else{
                                if(list1.size()>num){
                                    for(int i=0;i<num;i++){
                                        list2.add(list1.get(i));
                                    }
                                }else{
                                    for(int i=0;i<list1.size();i++){
                                        list2.add(list1.get(i));
                                    }

                                }
                            }
                            if(list2.size()>=list1.size()){
                                XUtils.show("没有更多数据");
                            }else{
                                XUtils.show("刷新成功");
                            }
                            headerNews.clear();
                            int N = list2.size()>2?3:list2.size();
                            Log.i("aaaaaa", "success: "+list2.size());
                            for(int i = 0;i<N;i++){
                                headerNews.add(list2.get(i));
                            }
                            list2.removeAll(headerNews);
                            vpheader.getAdapter().notifyDataSetChanged();
                            indicator.notifyDataSetChanged();
                            otherNews.clear();
                            //headertitle.setText(headerNews.get(0).getTitle());
                        }
                        otherNews.addAll(list2);
                        otherAdapter.notifyDataSetChanged();
                        page++;
                        header.setVisibility(View.VISIBLE);
                        imgNone.setVisibility(View.GONE);
                    }else{
                        if(isRefresh){
                            page =lastPage;
                        }
                        if(vpheader.getAdapter().getCount()<=0){
                            header.setVisibility(View.GONE);
                            imgNone.setVisibility(View.VISIBLE);
                        }
                    }


                } catch (JSONException e) {
                    header.setVisibility(View.GONE);
                    imgNone.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {///////
                super.onFailure(e, s);
                lv.onRefreshComplete();
            }
        },true);
    }
    private void loadData1(final boolean isRe){
        if( isRe &&tab != null){
            List<DataId> list = XUtils.findtypeNews(tab);
            List<DataId> list1 = new ArrayList<>();
            if(list != null && list.size()>0){
                headerNews.clear();
                if(list.size()>10){
                    for(int i=0;i<10;i++){
                        list1.add(list.get(i));
                    }
                }else{
                    for(int i=0;i<list.size();i++){
                        list1.add(list.get(i));
                    }
                }
                int N = list1.size()>2?3:list.size();
                for(int i = 0;i<N;i++){
                    headerNews.add(list1.get(i));
                }
                Log.i("whata", "load: "+N);
                list1.removeAll(headerNews);
                vpheader.getAdapter().notifyDataSetChanged();
                indicator.notifyDataSetChanged();
                otherNews.clear();
                //headertitle.setText(headerNews.get(0).getTitle());
                Log.i("whata", "loadData1: "+list1.size());
                otherNews.addAll(list1);
                otherAdapter.notifyDataSetChanged();
                page++;
                header.setVisibility(View.VISIBLE);
                imgNone.setVisibility(View.GONE);
            }
        }

    }
    private void loadTitle(){
        if(header == null){
            header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_header,null);
            vpheader = (MyViewPager) header.findViewById(R.id.header_vp);
            headertitle = (TextView) header.findViewById(R.id.header_title);
            indicator = (CirclePageIndicator) header.findViewById(R.id.header_indicator);
            vpheader.setAdapter(new vpAdapter());
            indicator.setViewPager(vpheader);
            if(headerNews!=null && headerNews.size()>0){
                headertitle.setText(headerNews.get(0).getTitle());
            }
            indicator.setOnPageChangeListener(pageLis);
            vpheader.setOnTouchListener(touchLis);
            handler.postDelayed(run,3000);
        }
    }
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            if(vpheader.getCurrentItem() +1<vpheader.getAdapter().getCount()){
                vpheader.setCurrentItem(vpheader.getCurrentItem()+1);
            }else{
                vpheader.setCurrentItem(0);
            }

        }
    };
    private View.OnTouchListener touchLis = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP && isMove){
                DataId news = headerNews.get(vpheader.getCurrentItem());
                NewsInfoActivity.startActivity(getActivity(),news.getUrl(),news.getTitle(),news.getThumbnailPicS());
            }else if(event.getAction() == MotionEvent.ACTION_DOWN){

                isMove = true;
            }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                ha.postDelayed(runn,100);
                //isMove = false;
            }
            return false;
        }
    };
    private Runnable runn = new Runnable() {
        @Override
        public void run() {
            isMove = false;
        }
    };
    private ViewPager.OnPageChangeListener pageLis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            headertitle.setText(headerNews.get(position).getTitle());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == 1){
                handler.removeCallbacks(run);
            }else if(state == 0){
                handler.postDelayed(run,3000);
            }
        }
    };
    class vpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return headerNews!=null?headerNews.size():0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView img = new ImageView(getActivity());
            XUtils.display(img,headerNews.get(position).getThumbnailPicS());
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.width =  ViewPager.LayoutParams.MATCH_PARENT;
            lp.height = ViewPager.LayoutParams.MATCH_PARENT;
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setLayoutParams(lp);

            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
    }
    }
    class OtherAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return otherNews!=null?otherNews.size():0;
        }

        @Override
        public DataId getItem(int position) {
            return otherNews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getNid();
        }


        @Override
        public View getView(int position, View v, ViewGroup parent) {
            ViewHolder holder = null;
            if(v == null){
                holder = new ViewHolder();
                v = getActivity().getLayoutInflater().inflate(R.layout.layout_news_item,null);
                holder.imgThumb = (ImageView) v.findViewById(R.id.item_news_thumb);
                holder.tvResume = (TextView) v.findViewById(R.id.item_news_resume);
                holder.tvTitle = (TextView) v.findViewById(R.id.item_news_title);
                v.setTag(holder);
            }else{
                holder = (ViewHolder) v.getTag();
            }
            DataId n = getItem(position);
            XUtils.display(holder.imgThumb,n.getThumbnailPicS());
            holder.tvTitle.setText(n.getTitle());
            holder.tvResume.setText(n.getAuthorName());
            return v;
        }
    }
    class ViewHolder{
        TextView tvTitle;
        TextView tvResume;
        ImageView imgThumb;
    }
}
