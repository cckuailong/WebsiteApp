package com.example.hasee.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hasee.news.Fragment.FirstPageFragment;
import com.example.hasee.news.adapter.MainTabAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;//顶部标题选项布局
    private ViewPager mviewPager;
    private String[] title;
    private MainTabAdapter madapter_title;//标题的适配器
    private List<FirstPageFragment> mFirstFraments;//存放fragment的集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        setdata();
        initView();
        initListener();
    }

    private void initListener() {
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                for(int i = 0;i<mFirstFraments.size();i++){
                    mFirstFraments.get(position).setPositoin(position);
                }
               // Log.i("aaaaaaa", "onTabSelected: ---->" +tab.getPosition());
                mviewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setdata(){
        title = new String[]{"要闻","体育","财经","军事","科技","历史","两性"};
        mFirstFraments = new ArrayList<FirstPageFragment>();
        for (int i = 0; i < title.length ; i++) {
            FirstPageFragment first = new FirstPageFragment();
            mFirstFraments.add(first);
        }

    }

    //初始化布局
    private void initView() {

        mTablayout = (TabLayout) findViewById(R.id.tab_title);
        mviewPager = (ViewPager) findViewById(R.id.viewPager);
        madapter_title = new MainTabAdapter(getSupportFragmentManager(),
                mFirstFraments,title);
        mviewPager.setAdapter(madapter_title);
        //直接一步搞定  tablayout绑定viewpager
        mTablayout.setupWithViewPager(mviewPager);


    }
}

